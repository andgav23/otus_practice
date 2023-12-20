package citrus.cucumber.steps;

import static org.citrusframework.actions.EchoAction.Builder.echo;
import static org.citrusframework.actions.SendMessageAction.Builder.send;
import citrus.services.MqService;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import org.citrusframework.TestCaseRunner;
import org.citrusframework.annotations.CitrusResource;
import org.springframework.beans.factory.annotation.Autowired;

public class MqHelperSteps {

  @CitrusResource
  private TestCaseRunner runner;
  private String ss;
  @Autowired
  private MqService mqService;


  @Если("Отправить сообщение {string}")
public void messagePayloadShouldBeMatch(String message){
mqService.sendMqMessage(runner, message);
  }
  @Тогда("Количество сообщений в очереди {int}")
  public void queueSizeShouldBeEquals(int queueSize) {
    mqService.checkQueueSize(queueSize);
  }
  @Тогда("Сообщение в очереди совпадает с {string}")
  public void messagePayloadShouldBeMath(String message){
    mqService.checkMessage(runner, message);
  }

}
