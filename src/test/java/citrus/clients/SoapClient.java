package citrus.clients;

import static org.citrusframework.ws.actions.SoapActionBuilder.soap;

import static org.example.utils.XmlBuilder.xmlFromFile;
import lombok.NoArgsConstructor;
import org.citrusframework.TestActionRunner;
import org.citrusframework.ws.actions.ReceiveSoapMessageAction;
import org.citrusframework.ws.client.WebServiceClient;
@NoArgsConstructor
public class SoapClient {

  public ReceiveSoapMessageAction.SoapMessageBuilderSupport soapSend(TestActionRunner actions, WebServiceClient userClient, String file) {
    actions.$(soap()
        .client(userClient)
        .send()
        .message()
        .soapAction("getUser")
        .body(xmlFromFile(file)));

    return soap()
        .client(userClient)
        .receive()
        .message();
  }
}
