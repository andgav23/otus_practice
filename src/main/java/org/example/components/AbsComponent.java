package org.example.components;

import org.example.actions.AbsActions;
import org.example.annotations.WebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AbsComponent<T> extends AbsActions {
  {
    this.standartWaiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(getComponentLocator()));
  }
  protected String baseLocator;
  protected Actions actions;


  public AbsComponent(WebDriver driver) {
    super(driver);
    actions = new Actions(driver);
  }

  private By getComponentLocator() {
    WebComponent webComponent = getClass().getAnnotation(WebComponent.class);

    if (webComponent != null) {
      String value = webComponent.value();

      baseLocator = value;

      if(value.startsWith("/")) {
        return By.xpath(value);
      }
      return By.cssSelector(value);
    }

    return null;
  }
}
