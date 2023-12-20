package citrus.clients;

import static org.citrusframework.http.actions.HttpActionBuilder.http;

import lombok.NoArgsConstructor;
import org.apache.hc.core5.http.ContentType;
import org.citrusframework.TestActionRunner;
import org.citrusframework.http.actions.HttpClientResponseActionBuilder;
import org.citrusframework.http.client.HttpClient;
import org.citrusframework.message.MessageType;

@NoArgsConstructor
public class UserHttpClient {

  public HttpClientResponseActionBuilder.HttpMessageBuilderSupport httpSend(TestActionRunner actions, HttpClient userClient, String userId) {
    actions.$(http()
        .client(userClient)
        .send()
        .get("/users/get/" + userId)
        .message()
        .accept(ContentType.APPLICATION_JSON.getMimeType()));

    return http()
        .client(userClient)
        .receive()
        .response()
        .message()
        .type(MessageType.JSON);
  }
}
