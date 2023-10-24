package org.example.di;

import io.cucumber.guice.ScenarioScoped;
import org.example.factory.impl.WebDriverFactory;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiceScoped {
  public WebDriver driver = new WebDriverFactory().create();
}
