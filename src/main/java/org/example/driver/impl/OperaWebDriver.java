package org.example.driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.example.driver.IDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class OperaWebDriver implements IDriver {
  @Override
  public WebDriver newDriver(){

    OperaOptions operaOptions = new OperaOptions();
    operaOptions.addArguments("--start-fullscreen");
    operaOptions.addArguments("--enable-extensions");
    operaOptions.addArguments("homepage=about:blank");
    downloadLocalWebDriver(DriverManagerType.OPERA);
    return new OperaDriver(operaOptions);

  }

  @Override
  public void downloadLocalWebDriver(DriverManagerType driverType) {
    WebDriverManager.getInstance(driverType).setup();
  }
}
