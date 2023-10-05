package org.example.driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.example.driver.IDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxWebDriver implements IDriver {
  @Override
  public WebDriver newDriver(){

    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments("--start-fullscreen");
    firefoxOptions.addArguments("--enable-extensions");
    firefoxOptions.addArguments("homepage=about:blank");
    downloadLocalWebDriver(DriverManagerType.FIREFOX);
    return new FirefoxDriver(firefoxOptions);

  }

  @Override
  public void downloadLocalWebDriver(DriverManagerType driverType) {
    WebDriverManager.getInstance(driverType).setup();
  }
}
