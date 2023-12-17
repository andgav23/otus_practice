package citrus.services;

import static org.citrusframework.actions.SendMessageAction.Builder.send;
import static org.citrusframework.validation.json.JsonMessageValidationContext.Builder.json;
import static org.citrusframework.validation.json.JsonPathMessageValidationContext.Builder.jsonPath;
import citrus.clients.UserHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.junit.EmbeddedActiveMQExtension;
import org.citrusframework.TestCaseRunner;
import org.citrusframework.http.actions.HttpClientResponseActionBuilder;
import org.citrusframework.http.client.HttpClient;
import org.citrusframework.jms.endpoint.JmsEndpoint;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MqService {
  @RegisterExtension
  public EmbeddedActiveMQExtension server = new EmbeddedActiveMQExtension();
  @Autowired
  private JmsEndpoint MQEndpoint;


  public void sendMessage(TestCaseRunner runner){

    runner.$(send()
        .endpoint(MQEndpoint)
        .message()
        .body("payload"));

  }
}
