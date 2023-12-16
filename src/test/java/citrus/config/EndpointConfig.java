
package citrus.config;


import static org.citrusframework.actions.ExecuteSQLAction.Builder.sql;
import static org.example.utils.XmlBuilder.xmlFromFile;
import jakarta.jms.ConnectionFactory;
import lombok.SneakyThrows;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import org.apache.commons.dbcp.BasicDataSource;
import org.citrusframework.container.AfterSuite;
import org.citrusframework.container.BeforeSuite;
import org.citrusframework.container.SequenceAfterSuite;
import org.citrusframework.container.SequenceBeforeSuite;
import org.citrusframework.context.TestContextFactory;
import org.citrusframework.dsl.endpoint.CitrusEndpoints;
import org.citrusframework.endpoint.EndpointAdapter;
import org.citrusframework.endpoint.adapter.RequestDispatchingEndpointAdapter;
import org.citrusframework.endpoint.adapter.StaticResponseEndpointAdapter;
import org.citrusframework.endpoint.adapter.mapping.HeaderMappingKeyExtractor;
import org.citrusframework.endpoint.adapter.mapping.SimpleMappingStrategy;
import org.citrusframework.endpoint.adapter.mapping.SoapActionMappingKeyExtractor;
import org.citrusframework.jms.endpoint.JmsEndpoint;
import org.citrusframework.variable.GlobalVariables;
import org.citrusframework.ws.client.WebServiceClient;
import org.citrusframework.ws.server.WebServiceServer;
import org.citrusframework.xml.XsdSchemaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.soap.SoapMessageFactory;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.xml.xsd.SimpleXsdSchema;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class EndpointConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("vm://0");
    }

    @Bean(name="MQEndpoint")
    public JmsEndpoint todoJmsEndpoint(ConnectionFactory connectionFactory) {
        return CitrusEndpoints.
            jms()
                .asynchronous()
                .connectionFactory(connectionFactory)
                .destination("otus.queue")
            .build();
    }

    @Bean
    public BeforeSuite beforeSuite(BasicDataSource todoListDataSource) {
        return new SequenceBeforeSuite.Builder()
            .actions(sql(todoListDataSource)
                .statement("CREATE TABLE IF NOT EXISTS otus_students " +
                    "(id NUMBER, name VARCHAR(50))")
                .statement("INSERT INTO otus_students VALUES(1,'Andrey')"))
            .build();
    }

//    @Bean
//    public AfterSuite afterSuite(BasicDataSource todoListDataSource) {
//        return new SequenceAfterSuite.Builder()
//            .actions(sql(todoListDataSource)
//                .statement("DELETE FROM otus_students"))
//            .build();
//    }

    @Bean(destroyMethod = "close", name="helperDataSource")
    public BasicDataSource helperDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(5);
        dataSource.setMaxIdle(2);
        return dataSource;
    }
//    SOAP

    @Bean
    public SimpleXsdSchema userSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/User.xsd"));
    }

    @Bean
    public XsdSchemaRepository schemaRepository() {
        XsdSchemaRepository schemaRepository = new XsdSchemaRepository();
        schemaRepository.getSchemas().add(userSchema());
        return schemaRepository;
    }

    @Bean
    public SoapMessageFactory messageFactory() {
        return new SaajSoapMessageFactory();
    }

    @Bean
    public WebServiceClient userClient() {
        return CitrusEndpoints
            .soap()
            .client()
            .defaultUri("http://localhost:8080/services/ws/user")
            .build();
    }

    @Bean
    public WebServiceServer userServer(TestContextFactory contextFactory) {
        return CitrusEndpoints
            .soap()
            .server()
            .port(8080)
            .endpointAdapter(dispatchingEndpointAdapter(contextFactory))
            .timeout(10000)
            .autoStart(true)
            .build();
    }

    @Bean
    public RequestDispatchingEndpointAdapter dispatchingEndpointAdapter(TestContextFactory contextFactory) {
        RequestDispatchingEndpointAdapter dispatchingEndpointAdapter = new RequestDispatchingEndpointAdapter();
        dispatchingEndpointAdapter.setMappingKeyExtractor(mappingKeyExtractor());
        dispatchingEndpointAdapter.setMappingStrategy(mappingStrategy(contextFactory));
        return dispatchingEndpointAdapter;
    }

    @Bean
    public HeaderMappingKeyExtractor mappingKeyExtractor() {
        return new SoapActionMappingKeyExtractor();
    }

    @Bean
    public SimpleMappingStrategy mappingStrategy(TestContextFactory contextFactory) {
        SimpleMappingStrategy mappingStrategy = new SimpleMappingStrategy();
        Map<String, EndpointAdapter> mappings = new HashMap<>();
        mappings.put("getUser", userResponseAdapter(contextFactory));
        mappingStrategy.setAdapterMappings(mappings);
        return mappingStrategy;
    }

    @SneakyThrows
    @Bean
    public EndpointAdapter userResponseAdapter(TestContextFactory contextFactory) {
        StaticResponseEndpointAdapter endpointAdapter = new StaticResponseEndpointAdapter();
        endpointAdapter.setMessagePayload(xmlFromFile("xml/SOAPResponse.xml"));
        endpointAdapter.setTestContextFactory(contextFactory);
        return endpointAdapter;
    }


}
