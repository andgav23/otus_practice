package citrus.cucumber.steps;

import citrus.services.SeleniumService;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.citrusframework.TestCaseRunner;
import org.citrusframework.annotations.CitrusResource;
import org.springframework.beans.factory.annotation.Autowired;

public class SeleniumHelperSteps {

  @CitrusResource
  private TestCaseRunner runner;

  @Autowired
  private SeleniumService seleniumService;


  @Пусть("Открыт браузер Chrome")
  public void openChrome() {
    seleniumService.openBrowser(runner);
  }

  @Если("Открыть страницу {string}")
  public void openPageByUrl(String pageUrl){
    seleniumService.openUrl(runner, pageUrl);
  }

  @Тогда("Открыта страница с заголовком {string}")
  public void pageShouldBeHaveHeaderAs(String expectedHeader){
    seleniumService.checkHeader(runner, expectedHeader);
  }


}
