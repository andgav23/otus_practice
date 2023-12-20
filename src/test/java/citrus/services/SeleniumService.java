package citrus.services;

import static org.citrusframework.selenium.actions.SeleniumActionBuilder.selenium;

import lombok.extern.slf4j.Slf4j;
import org.citrusframework.TestCaseRunner;
import org.citrusframework.selenium.endpoint.SeleniumBrowser;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SeleniumService {

  @Autowired
  private SeleniumBrowser browser;
  public void openBrowser(TestCaseRunner runner){
    runner.$(selenium()
        .browser(browser)
        .start());
  }

  public void openUrl(TestCaseRunner runner, String pageUrl) {
    runner.$(selenium()
        .navigate(pageUrl));
  }

  public void checkHeader(TestCaseRunner runner, String expectedHeader) {
    runner.$(selenium().find().element(By.xpath("//h2"))
        .enabled(true)
        .displayed(true)
        .text(expectedHeader));
  }
}
