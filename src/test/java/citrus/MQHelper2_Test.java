package citrus;

import static org.citrusframework.actions.SendMessageAction.Builder.send;
import org.apache.activemq.artemis.junit.EmbeddedActiveMQExtension;
import org.citrusframework.TestActionRunner;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.annotations.CitrusTest;
import org.citrusframework.config.CitrusSpringConfig;
import org.citrusframework.jms.endpoint.JmsEndpoint;
import org.citrusframework.junit.jupiter.spring.CitrusSpringSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@CitrusSpringSupport
@ContextConfiguration(classes = CitrusSpringConfig.class)
public class MQHelper2_Test {
  @RegisterExtension
  public EmbeddedActiveMQExtension server = new EmbeddedActiveMQExtension();


  @Autowired
  private JmsEndpoint MQEndpoint;

  @Test
  @CitrusTest
  public void messageShouldBeAddedToQueue(@CitrusResource TestActionRunner actions) {

    server.start();
    actions.$(send()
        .endpoint(MQEndpoint)
        .message()
        .body("payload"));
    server.stop();
  }
}
