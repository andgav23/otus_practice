package citrus.cucumber.steps;

import static org.citrusframework.actions.SendMessageAction.Builder.send;
import citrus.services.HttpService;
import citrus.services.MqService;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import org.apache.activemq.artemis.junit.EmbeddedActiveMQExtension;
import org.citrusframework.TestCaseRunner;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.http.actions.HttpClientResponseActionBuilder;
import org.citrusframework.jms.endpoint.JmsEndpoint;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;

public class MqHelperSteps {
//  @RegisterExtension
//  public EmbeddedActiveMQExtension server = new EmbeddedActiveMQExtension();
  @CitrusResource
  private TestCaseRunner runner;
//  @Autowired
//  private JmsEndpoint MQEndpoint;
  @Autowired
  private MqService mqService;


  @Если("Отправить сообщение {string}")
public void getUserData(String payload){
mqService.sendMessage(runner);
//    server.start();
//    runner.$(send()
//        .endpoint(MQEndpoint)
//        .message()
//        .body(payload));
//    server.stop();
  }

}
