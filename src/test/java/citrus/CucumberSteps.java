package citrus;

import static org.example.utils.XmlBuilder.xmlFromFile;
import citrus.clients.SoapClient;
import io.cucumber.java.en.Given;

import io.cucumber.spring.CucumberContextConfiguration;
import org.citrusframework.TestActionRunner;
import org.citrusframework.TestCaseRunner;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.config.CitrusSpringConfig;
import org.citrusframework.ws.client.WebServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = CitrusSpringConfig.class)
public class CucumberSteps {
  @CitrusResource
  private TestCaseRunner runner;
  @Autowired
  private WebServiceClient userClient;
  @Given("Todo list is empty")
  public void tableShouldBeContainRows() {

    SoapClient client = new SoapClient();
    runner.$(client.getUser(runner, userClient).body(xmlFromFile("xml/SOAPResponse.xml")));
  }
}
