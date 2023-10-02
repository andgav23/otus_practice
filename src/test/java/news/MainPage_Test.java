package news;

import org.example.annotations.Driver;
import org.example.extentions.UIExtention;
import org.example.pages.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(UIExtention.class)
public class MainPage_Test {
  @Driver
  private WebDriver driver;

  //  private WebDriver driver;
  //
  //  @BeforeEach
  //  public void init() {
  //    driver = new FactoryWebDriver().create(); // нежелательно, поскольку жестко привязывает к реализации фабрики драйвера
  //  }

  @Test
  public void mainPageOpened() {
    new MainPage(driver).open();

  }
}
