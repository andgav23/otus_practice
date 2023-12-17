package citrus;

import static org.example.utils.XmlBuilder.xmlFromFile;
import citrus.clients.SoapClient;


import citrus.services.SoapService;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.citrusframework.TestCaseRunner;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.config.CitrusSpringConfig;
import org.citrusframework.ws.actions.ReceiveSoapMessageAction;
import org.citrusframework.ws.client.WebServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = CitrusSpringConfig.class)
@Slf4j
public class CucumberSteps {
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
