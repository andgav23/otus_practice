package citrus;

import static org.citrusframework.ws.actions.SoapActionBuilder.soap;
import static org.example.utils.XmlBuilder.xmlFromFile;
import org.citrusframework.TestActionRunner;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.annotations.CitrusTest;
import org.citrusframework.config.CitrusSpringConfig;
import org.citrusframework.junit.jupiter.spring.CitrusSpringSupport;
import org.citrusframework.spi.Resource;
import org.citrusframework.ws.client.WebServiceClient;
import org.citrusframework.xml.Marshaller;
import org.example.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.nio.charset.Charset;

@CitrusSpringSupport
@ContextConfiguration(classes = CitrusSpringConfig.class)
public class SOAPHelper_Test {
  @Autowired
  private WebServiceClient todoClient;
  @Autowired
  Marshaller xmlMarshaller;

  @Test
  @CitrusTest
  public void tableShouldBeContainRows(@CitrusResource TestActionRunner actions) throws IOException {
    System.out.println(StreamUtils.copyToString(new ClassPathResource("xml/SOAPResponse.xml").getInputStream(), Charset.defaultCharset()));
    actions.$(soap()
        .client(todoClient)
        .send()
        .message()
        .soapAction("getTodo")
        .body("<todo:getTodoRequest xmlns:todo=\"http://citrusframework.org/samples/todolist\"></todo:getTodoRequest>"));

    actions.$(soap()
        .client(todoClient)
        .receive()
        .message()
        .body(xmlFromFile("xml/SOAPResponse.xml")));
  }
}
