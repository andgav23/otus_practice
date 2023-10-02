package org.example.factory.impl;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeSettings implements IBrowserSettings {

  @Override
  public MutableCapabilities configureDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--start-fullscreen");
    chromeOptions.addArguments("--enable-extentions");
    return chromeOptions;
  }
}
