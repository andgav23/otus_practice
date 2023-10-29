package org.example.driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.example.driver.IDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeWebDriver implements IDriver {
  @Override
  public WebDriver newDriver(){

    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--start-fullscreen");
    chromeOptions.addArguments("--enable-extensions");
    chromeOptions.addArguments("homepage=about:blank");
    chromeOptions.addArguments("--start-maximized");
    downloadLocalWebDriver(DriverManagerType.CHROME);
    return new ChromeDriver(chromeOptions);

  }

  @Override
  public void downloadLocalWebDriver(DriverManagerType driverType) {
    WebDriverManager.getInstance(driverType).setup();
  }
}
