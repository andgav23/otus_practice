package citrus.services;

import static org.example.utils.XmlBuilder.xmlFromFile;
import citrus.clients.SoapClient;
import org.citrusframework.TestCaseRunner;
import org.citrusframework.ws.actions.ReceiveSoapMessageAction;
import org.citrusframework.ws.client.WebServiceClient;
import org.springframework.beans.factory.annotation.Autowired;

public class SoapService {
  @Autowired
  private WebServiceClient userClient;

  public ReceiveSoapMessageAction.SoapMessageBuilderSupport sendSoapMessage(TestCaseRunner runner, String file) {
    SoapClient client = new SoapClient();
    return client.soapSend(runner, userClient, file);
  }

  public void checkResponse(TestCaseRunner runner, ReceiveSoapMessageAction.SoapMessageBuilderSupport soapResponse, String content) {
    runner.$(soapResponse.body(xmlFromFile(content)));
  }
}
