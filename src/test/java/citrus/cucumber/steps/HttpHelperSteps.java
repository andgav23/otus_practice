package citrus.cucumber.steps;

import citrus.services.HttpService;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import io.cucumber.spring.CucumberContextConfiguration;
import org.citrusframework.TestActionRunner;
import org.citrusframework.TestCaseRunner;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.config.CitrusSpringConfig;
import org.citrusframework.http.actions.HttpClientActionBuilder;
import org.citrusframework.http.actions.HttpClientResponseActionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

public class HttpHelperSteps {
  @CitrusResource
  private TestCaseRunner runner;

  @Autowired
  private HttpService httpService;
  private HttpClientResponseActionBuilder.HttpMessageBuilderSupport httpResponse;


  @Если("Запросить данные о пользователе {string}")
public void getUserData(String userId){
    httpResponse = httpService.getUserById(runner, userId);
  }
  @Тогда("Ответ соответствует json схеме User")
  public void responseShouldBeMatchSchema() {
    httpService.checkUserDataSchema(runner, httpResponse);
  }
  @Тогда("Получен класс пользователя {int}")
  public void gradeShouldBeEquals(int grade){
    httpService.checkGrade(runner, httpResponse, grade);
  }
}