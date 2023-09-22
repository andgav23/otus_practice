package org.example.factory.impl;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeSettings implements IBrowserSettings {

    @Override
    public void configureDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
    }
}
