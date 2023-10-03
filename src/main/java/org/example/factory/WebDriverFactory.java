package org.example.factory;


import org.example.driver.impl.ChromeWebDriver;
import org.example.exceptions.BrowserNotSupportedExceptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WebDriverFactory {
  private String browserName = System.getProperty("browser", "chrome");

  public EventFiringWebDriver create() {
    switch (this.browserName) {
      case "chrome": {
        return new EventFiringWebDriver(new ChromeWebDriver().newDriver());
      }
    }
    throw new BrowserNotSupportedExceptions(browserName);
  }
}
