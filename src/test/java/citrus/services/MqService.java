package citrus.services;

import static org.citrusframework.actions.EchoAction.Builder.echo;
import static org.citrusframework.actions.ReceiveMessageAction.Builder.receive;
import static org.citrusframework.actions.SendMessageAction.Builder.send;
import static org.example.utils.JsonBuilder.jsonFromFileToString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.junit.EmbeddedActiveMQExtension;
import org.citrusframework.TestCaseRunner;
import org.citrusframework.jms.endpoint.JmsEndpoint;
import org.citrusframework.variable.GlobalVariables;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MqService {
  private final String QUEUE_NAME = "otus.queue";
  @RegisterExtension
  public EmbeddedActiveMQExtension server = new EmbeddedActiveMQExtension();
  @Autowired
  private JmsEndpoint MQEndpoint;

  public void sendMqMessage(TestCaseRunner runner, String message){

    runner.$(send()
        .endpoint(MQEndpoint)
        .message()
        .body(jsonFromFileToString(message)));

  }

  public void checkQueueSize(int queueSize) {
    assertEquals(queueSize, server.getMessageCount(QUEUE_NAME));
  }

  public void checkMessage(TestCaseRunner runner, String message){

    runner.$(receive()
        .endpoint(MQEndpoint)
        .message()
        .body(jsonFromFileToString(message)));

  }


}
