package citrus.cucumber.steps;

import citrus.services.SoapService;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import org.citrusframework.TestCaseRunner;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.ws.actions.ReceiveSoapMessageAction;
import org.springframework.beans.factory.annotation.Autowired;

public class SoapHelperSteps {
  @CitrusResource
  private TestCaseRunner runner;
  @Autowired
  private SoapService soapService;
  private ReceiveSoapMessageAction.SoapMessageBuilderSupport soapResponse;

  @Если("Отправить xml файл {string}")
  public void sendSoapMessage(String file) {
    soapResponse = soapService.sendSoapMessage(runner, file);
  }

  @Тогда("Получен ответ по схеме xsd с содержимым {string}")
  public void responseShouldBeByXsdAdnContainContent(String content) {
    soapService.checkResponse(runner, soapResponse, content);
  }

}
