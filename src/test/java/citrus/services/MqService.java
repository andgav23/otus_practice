package citrus.services;

import static org.citrusframework.actions.ReceiveMessageAction.Builder.receive;
import static org.citrusframework.actions.SendMessageAction.Builder.send;
import static org.example.utils.JsonBuilder.jsonFromFileToString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.junit.EmbeddedActiveMQExtension;
import org.citrusframework.TestCaseRunner;
import org.citrusframework.jms.endpoint.JmsEndpoint;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MqService {
  @RegisterExtension
  public EmbeddedActiveMQExtension server = new EmbeddedActiveMQExtension();
  @Autowired
  private JmsEndpoint mqEndpoint;

  public void sendMqMessage(TestCaseRunner runner, String message) {
    runner.$(send()
        .endpoint(mqEndpoint)
        .message()
        .body(jsonFromFileToString(message)));
  }

  public void checkQueueSize(int queueSize) {
    assertEquals(queueSize, server.getMessageCount("otus.queue"));
  }

  public void checkMessage(TestCaseRunner runner, String message) {
    runner.$(receive()
        .endpoint(mqEndpoint)
        .message()
        .body(jsonFromFileToString(message)));
  }


}
