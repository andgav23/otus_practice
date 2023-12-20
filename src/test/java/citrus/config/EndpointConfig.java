
package citrus.config;


import static org.citrusframework.actions.ExecuteSQLAction.Builder.sql;
import static org.example.utils.JsonBuilder.jsonFromFileToString;
import static org.example.utils.XmlBuilder.xmlFromFile;
import citrus.services.DbService;
import citrus.services.HttpService;
import citrus.services.MqService;
import citrus.services.SoapService;
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
import org.citrusframework.http.client.HttpClient;
import org.citrusframework.http.message.HttpMessageHeaders;
import org.citrusframework.http.server.HttpServer;
import org.citrusframework.jms.endpoint.JmsEndpoint;
import org.citrusframework.json.JsonSchemaRepository;

import org.citrusframework.spi.Resources;
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
import org.citrusframework.json.schema.SimpleJsonSchema;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class EndpointConfig {

  @Bean
  public GlobalVariables globalVariables() {
    GlobalVariables variables = new GlobalVariables();
    variables.getVariables().put("todoId", "702c4a4e-5c8a-4ce2-a451-4ed435d3604a");
    variables.getVariables().put("todoName", "todo_1871");
    variables.getVariables().put("todoDescription", "Description: todo_1871");
    return variables;
  }

  // MQ
  @Bean
  public ConnectionFactory connectionFactory() {
    return new ActiveMQConnectionFactory("vm://0");
  }

  @Bean(name = "MQEndpoint")
  public JmsEndpoint todoJmsEndpoint(ConnectionFactory connectionFactory) {
    return CitrusEndpoints.
        jms()
        .asynchronous()
        .connectionFactory(connectionFactory)
        .destination("otus.queue")
        .build();
  }

  @Bean
  public MqService mqService() {
    return new MqService();
  }

  // DB
  @Bean
  public BeforeSuite beforeSuite(BasicDataSource todoListDataSource) {
    return new SequenceBeforeSuite.Builder()
        .actions(sql(todoListDataSource)
            .statement("CREATE TABLE IF NOT EXISTS users " +
                "(id VARCHAR(20), name VARCHAR(50), school_name VARCHAR(50), city VARCHAR(20), grade NUMBER )"))
        .build();
  }

  @Bean
  public AfterSuite afterSuite(BasicDataSource todoListDataSource) {
    return new SequenceAfterSuite.Builder()
        .actions(sql(todoListDataSource)
            .statement("DELETE FROM users"))
        .build();
  }

  @Bean(destroyMethod = "close", name = "helperDataSource")
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

  @Bean
  public DbService dbService() {
    return new DbService();
  }

  // SOAP
  @Bean
  public SimpleXsdSchema userXsdSchema() {
    return new SimpleXsdSchema(new ClassPathResource("xsd/User.xsd"));
  }

  @Bean
  public XsdSchemaRepository schemaRepository() {
    XsdSchemaRepository schemaRepository = new XsdSchemaRepository();
    schemaRepository.getSchemas().add(userXsdSchema());
    return schemaRepository;
  }

  @Bean
  public SoapMessageFactory soapMessageFactory() {
    return new SaajSoapMessageFactory();
  }

  @Bean
  public WebServiceClient soapUserClient() {
    return CitrusEndpoints
        .soap()
        .client()
        .defaultUri("http://localhost:8080/services/ws/user")
        .build();
  }

  @Bean
  public WebServiceServer soapUserServer(TestContextFactory contextFactory) {
    return CitrusEndpoints
        .soap()
        .server()
        .port(8080)
        .endpointAdapter(soapDispatchingEndpointAdapter(contextFactory))
        .timeout(10000)
        .autoStart(true)
        .build();
  }

  @Bean
  public RequestDispatchingEndpointAdapter soapDispatchingEndpointAdapter(TestContextFactory contextFactory) {
    RequestDispatchingEndpointAdapter dispatchingEndpointAdapter = new RequestDispatchingEndpointAdapter();
    dispatchingEndpointAdapter.setMappingKeyExtractor(soapMappingKeyExtractor());
    dispatchingEndpointAdapter.setMappingStrategy(soapMappingStrategy(contextFactory));
    return dispatchingEndpointAdapter;
  }

  @Bean
  public HeaderMappingKeyExtractor soapMappingKeyExtractor() {
    return new SoapActionMappingKeyExtractor();
  }

  @Bean
  public SimpleMappingStrategy soapMappingStrategy(TestContextFactory contextFactory) {
    SimpleMappingStrategy mappingStrategy = new SimpleMappingStrategy();
    Map<String, EndpointAdapter> mappings = new HashMap<>();
    mappings.put("getUser", soapUserResponseAdapter(contextFactory));
    mappingStrategy.setAdapterMappings(mappings);
    return mappingStrategy;
  }

  @SneakyThrows
  @Bean
  public EndpointAdapter soapUserResponseAdapter(TestContextFactory contextFactory) {
    StaticResponseEndpointAdapter endpointAdapter = new StaticResponseEndpointAdapter();
    endpointAdapter.setMessagePayload(xmlFromFile("xml/SOAPResponse.xml"));
    endpointAdapter.setTestContextFactory(contextFactory);
    return endpointAdapter;
  }

  @Bean(name = "soapService")
  public SoapService soapService() {
    return new SoapService();
  }

  //HTTP
  @Bean
  public HttpClient httpUserClient() {
    return CitrusEndpoints
        .http()
        .client()
        .requestUrl("http://localhost:8081")
        .build();
  }

  @Bean
  public HttpServer httpUserServer(TestContextFactory contextFactory) throws Exception {
    return CitrusEndpoints
        .http()
        .server()
        .port(8081)
        .endpointAdapter(httpDispatchingEndpointAdapter(contextFactory))
        .timeout(10000)
        .autoStart(true)
        .build();
  }

  @Bean
  public RequestDispatchingEndpointAdapter httpDispatchingEndpointAdapter(TestContextFactory contextFactory) {
    RequestDispatchingEndpointAdapter dispatchingEndpointAdapter = new RequestDispatchingEndpointAdapter();
    dispatchingEndpointAdapter.setMappingKeyExtractor(mappingKeyExtractor());
    dispatchingEndpointAdapter.setMappingStrategy(httpMappingStrategy(contextFactory));
    return dispatchingEndpointAdapter;
  }

  @Bean
  public HeaderMappingKeyExtractor mappingKeyExtractor() {
    HeaderMappingKeyExtractor mappingKeyExtractor = new HeaderMappingKeyExtractor();
    mappingKeyExtractor.setHeaderName(HttpMessageHeaders.HTTP_REQUEST_URI);
    return mappingKeyExtractor;
  }

  @Bean
  public SimpleMappingStrategy httpMappingStrategy(TestContextFactory contextFactory) {
    SimpleMappingStrategy mappingStrategy = new SimpleMappingStrategy();
    Map<String, EndpointAdapter> mappings = new HashMap<>();
    mappings.put("/users/get/user-id1", httpUserResponseAdapter(contextFactory));
    mappingStrategy.setAdapterMappings(mappings);
    return mappingStrategy;
  }

  @Bean
  public EndpointAdapter httpUserResponseAdapter(TestContextFactory contextFactory) {
    StaticResponseEndpointAdapter endpointAdapter = new StaticResponseEndpointAdapter();
    endpointAdapter.setMessagePayload(jsonFromFileToString("__files/json/user-id1.json"));
    endpointAdapter.setTestContextFactory(contextFactory);
    return endpointAdapter;
  }

  @Bean
  public JsonSchemaRepository jsonSchemaRepository() {
    JsonSchemaRepository repository = new JsonSchemaRepository();
    repository.getSchemas().add(userSchema());
    return repository;
  }

  @Bean
  public SimpleJsonSchema userSchema() {
    return new SimpleJsonSchema(new Resources.ClasspathResource("__files/jsonSchema/user_schema.json"));
  }

  @Bean
  public HttpService httpService() {
    return new HttpService();
  }

}
