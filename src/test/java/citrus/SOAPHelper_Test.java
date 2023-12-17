package citrus;

import static org.example.utils.XmlBuilder.xmlFromFile;
import citrus.clients.SoapClient;
import lombok.SneakyThrows;
import org.citrusframework.TestActionRunner;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.config.CitrusSpringConfig;
import org.citrusframework.junit.jupiter.spring.CitrusSpringSupport;
import org.citrusframework.ws.client.WebServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


@CitrusSpringSupport
@ContextConfiguration(classes = CitrusSpringConfig.class)
public class SOAPHelper_Test {
  @Autowired
  private WebServiceClient userClient;

  @SneakyThrows
  @Test
//  @CitrusTest
  public void tableShouldBeContainRows(@CitrusResource TestActionRunner actions) {
//
//    SoapClient client = new SoapClient();
//    actions.$(client.soapSend(actions, userClient).body(xmlFromFile("xml/SOAPResponse.xml")));
  }
}
