package org.example.factory.impl;


import com.google.inject.Inject;
import org.example.driver.impl.ChromeWebDriver;
import org.example.driver.impl.FirefoxWebDriver;
import org.example.driver.impl.OperaWebDriver;
import org.example.exceptions.BrowserNotSupportedExceptions;
import org.example.factory.IWebDriverFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WebDriverFactory implements IWebDriverFactory {

  public EventFiringWebDriver create(String browserName) {
    switch (browserName) {
      case "chrome" -> {
        return new EventFiringWebDriver(new ChromeWebDriver().newDriver());
      }
      case "firefox" -> {
        return new EventFiringWebDriver(new FirefoxWebDriver().newDriver());
      }
      case "opera" -> {
        return new EventFiringWebDriver(new OperaWebDriver().newDriver());
      }
      default -> throw new BrowserNotSupportedExceptions(browserName);
    }

  }
}
