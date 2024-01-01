package org.example.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidSelenideDriver implements WebDriverProvider {
  private String appiumHostName = System.getProperty("appium.hostname", "http://127.0.0.1:4723");

  @Nonnull
  @Override
  public WebDriver createDriver(@Nonnull Capabilities capabilities) {
    UiAutomator2Options options = new UiAutomator2Options();
    options.merge(capabilities);
    options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
    options.setPlatformName("Android");
    options.setDeviceName("otus_device");
    options.setPlatformVersion("9");
    options.setAppPackage("com.pyankoff.andy");
    options.setAppActivity(".MainActivity");
    try {
      WebDriver driver = new AndroidDriver(new URL(appiumHostName), options);
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      return driver;
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
