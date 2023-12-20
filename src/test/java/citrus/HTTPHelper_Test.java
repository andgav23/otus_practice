package citrus;

import static org.citrusframework.http.actions.HttpActionBuilder.http;
import static org.example.utils.JsonBuilder.jsonFromFileToString;
import org.apache.hc.core5.http.ContentType;
import org.citrusframework.TestActionRunner;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.annotations.CitrusTest;
import org.citrusframework.http.client.HttpClient;
import org.citrusframework.message.MessageType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


public class HTTPHelper_Test {
  @Autowired
  private HttpClient httpUserClient;

  @Test
  @CitrusTest
  public void testTodo(@CitrusResource TestActionRunner actions) {
    actions.$(http()
        .client(httpUserClient)
        .send()
        .get("/users/get/user-id1")
        .message()
        .accept(ContentType.APPLICATION_JSON.getMimeType()));

    actions.$(http()
        .client(httpUserClient)
        .receive()
        .response(HttpStatus.OK)
        .message()
        .type(MessageType.JSON)
        .body(jsonFromFileToString("__files/json/user-id1.json")));
  }

  }

