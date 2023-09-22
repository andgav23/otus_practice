package org.example.factory;


import org.openqa.selenium.WebDriver;

public class FactoryWebDriver {
    private String browserName = System.getProperty("browser", "chrome");

    public WebDriver create() {
        switch (browserName) {
            case "chrome": {

            }
        }

        return null;
    }
}
