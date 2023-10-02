package org.example.factory;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.exceptions.BrowserNotSupportedExceptions;
import org.example.factory.impl.ChromeSettings;
import org.example.factory.impl.IBrowserSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FactoryWebDriver {
  private String browserName = System.getProperty("browser", "chrome");

  public WebDriver create() {
    switch (browserName) {
      case "chrome": {
        WebDriverManager.chromedriver().setup();
        IBrowserSettings chromeOptions = new ChromeSettings();
        return new ChromeDriver(chromeOptions.configureDriver());
      }
    }
    throw new BrowserNotSupportedExceptions(browserName);
  }
}
