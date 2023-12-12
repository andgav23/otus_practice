
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
import org.citrusframework.xml.Jaxb2Marshaller;
import org.citrusframework.xml.Marshaller;
import org.citrusframework.xml.XsdSchemaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.ws.soap.SoapMessageFactory;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.xml.xsd.SimpleXsdSchema;
import java.nio.charset.Charset;
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

    @Bean
    public AfterSuite afterSuite(BasicDataSource todoListDataSource) {
        return new SequenceAfterSuite.Builder()
            .actions(sql(todoListDataSource)
                .statement("DELETE FROM otus_students"))
            .build();
    }

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
public GlobalVariables globalVariables() {
    GlobalVariables variables = new GlobalVariables();
    variables.getVariables().put("todoId", "702c4a4e-5c8a-4ce2-a451-4ed435d3604a");
    variables.getVariables().put("todoName", "todo_1871");
    variables.getVariables().put("todoDescription", "Description: todo_1871");
    return variables;
}
    @Bean
    public SimpleXsdSchema todoListSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/TodoList.xsd"));
    }

    @Bean
    public XsdSchemaRepository schemaRepository() {
        XsdSchemaRepository schemaRepository = new XsdSchemaRepository();
        schemaRepository.getSchemas().add(todoListSchema());
        return schemaRepository;
    }

    @Bean
    public SoapMessageFactory messageFactory() {
        return new SaajSoapMessageFactory();
    }

    @Bean
    public WebServiceClient todoClient() {
        return CitrusEndpoints
            .soap()
            .client()
            .defaultUri("http://localhost:8080/services/ws/todolist")
            .build();
    }

    @Bean
    public WebServiceServer todoListServer(TestContextFactory contextFactory) {
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

        mappings.put("getTodo", todoResponseAdapter(contextFactory));
        mappings.put("getTodoList", todoListResponseAdapter(contextFactory));

        mappingStrategy.setAdapterMappings(mappings);
        return mappingStrategy;
    }

    @SneakyThrows
    @Bean
    public EndpointAdapter todoResponseAdapter(TestContextFactory contextFactory) {
        StaticResponseEndpointAdapter endpointAdapter = new StaticResponseEndpointAdapter();
        endpointAdapter.setMessagePayload(xmlFromFile("xml/SOAPResponse.xml"));
        endpointAdapter.setTestContextFactory(contextFactory);
        return endpointAdapter;
    }

    @Bean
    public EndpointAdapter todoListResponseAdapter(TestContextFactory contextFactory) {
        StaticResponseEndpointAdapter endpointAdapter = new StaticResponseEndpointAdapter();
        endpointAdapter.setMessagePayload("<getTodoListResponse xmlns=\"http://citrusframework.org/samples/todolist\">" +
            "<list>" +
            "<todoEntry>" +
            "<id>${todoId}</id>" +
            "<title>${todoName}</title>" +
            "<description>${todoDescription}</description>" +
            "<done>false</done>" +
            "</todoEntry>" +
            "</list>" +
            "</getTodoListResponse>");
        endpointAdapter.setTestContextFactory(contextFactory);
        return endpointAdapter;
    }

}
