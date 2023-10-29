package example.steps.common;

import com.google.inject.Inject;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Пусть;
import org.example.di.GuiceScoped;
import org.example.factory.impl.WebDriverFactory;
import org.example.pages.MainPage;


public class CommonSteps {
  @Inject
  private GuiceScoped guiceScoped;

  @Дано("Открыт браузер {string}")
  public void openBrowser(String browserName) {
    guiceScoped.driver = new WebDriverFactory().create(browserName);
  }


}
