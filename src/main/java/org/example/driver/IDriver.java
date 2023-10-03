package org.example.driver;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;

public interface IDriver {

  WebDriver newDriver();
  void downloadLocalWebDriver(DriverManagerType driverType);
}
