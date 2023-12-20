package citrus.services;

import static org.citrusframework.validation.json.JsonMessageValidationContext.Builder.json;
import static org.citrusframework.validation.json.JsonPathMessageValidationContext.Builder.jsonPath;

import citrus.clients.UserHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.citrusframework.TestCaseRunner;
import org.citrusframework.http.actions.HttpClientResponseActionBuilder;
import org.citrusframework.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class HttpService {
  @Autowired
  private HttpClient userClient;

  public HttpClientResponseActionBuilder.HttpMessageBuilderSupport getUserById(TestCaseRunner runner, String userId) {
    UserHttpClient client = new UserHttpClient();
    return client.httpSend(runner, userClient, userId);
  }

  public void checkUserDataSchema(TestCaseRunner runner, HttpClientResponseActionBuilder.HttpMessageBuilderSupport httpResponse) {
    runner.$(httpResponse
        .validate(json().schemaValidation(true).schema("userSchema")));

  }

  public void checkGrade(TestCaseRunner runner, HttpClientResponseActionBuilder.HttpMessageBuilderSupport httpResponse, int grade) {
    runner.$(httpResponse
        .validate(jsonPath()
            .expression("$.grade", grade)));

  }

}
