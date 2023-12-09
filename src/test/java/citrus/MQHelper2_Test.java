package citrus;

import static org.citrusframework.actions.EchoAction.Builder.echo;
import static org.citrusframework.actions.ReceiveMessageAction.Builder.receive;
import static org.citrusframework.actions.SendMessageAction.Builder.send;
import static org.citrusframework.dsl.XpathSupport.xpath;
import static org.citrusframework.http.actions.HttpActionBuilder.http;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.junit.EmbeddedActiveMQExtension;
import org.citrusframework.TestActionRunner;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.annotations.CitrusTest;
import org.citrusframework.config.CitrusSpringConfig;
import org.citrusframework.http.client.HttpClient;
import org.citrusframework.jms.endpoint.JmsEndpoint;
import org.citrusframework.junit.jupiter.CitrusSupport;
import org.citrusframework.junit.jupiter.spring.CitrusSpringSupport;
import org.citrusframework.message.MessageType;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import javax.jms.*;

//@CitrusSupport
@CitrusSpringSupport
@ContextConfiguration(classes = CitrusSpringConfig.class)
public class MQHelper2_Test {
  @RegisterExtension
  public EmbeddedActiveMQExtension server = new EmbeddedActiveMQExtension();

//  @Test
//  public void myTest() {
//    // test something, eg. create a queue
//    server.createQueue("test.adress", "test.queue");
//  }

//  @Autowired
//  private HttpClient todoClient;

  @Autowired
  @Qualifier("todoJmsEndpoint")
  private JmsEndpoint todoJmsEndpoint;

//  @Autowired
//  @Qualifier("todoReportEndpoint")
//  private JmsEndpoint todoReportEndpoint;
//
//  @Autowired
//  @Qualifier("todoJmsSyncEndpoint")
//  private JmsEndpoint todoJmsSyncEndpoint;

  @Test
  //@CitrusTest
  public void testAddTodoEntry(@CitrusResource TestActionRunner actions) {

    server.start();
    actions.$(send()
        .endpoint(todoJmsEndpoint)
        .message()
        .body("payload"));
    server.stop();
  }
}
