package example.steps.common;

import io.cucumber.java.ru.Дано;

public class CommonSteps {

  @Дано("Открыт браузер {string}")
  public void openBrowser(String browserName) {
    System.out.println("Открыт браузер" + browserName);
  }
}
