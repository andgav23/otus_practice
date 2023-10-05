package org.example.actions;

import org.example.waiters.StandartWaiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public abstract class AbsActions<T> {
  protected WebDriver driver;
  protected StandartWaiter standartWaiter;

  public AbsActions(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);

    standartWaiter = new StandartWaiter(driver);
  }

  protected BiConsumer<By, Predicate<? super WebElement>> clickElementByPredicate = (By locator, Predicate<? super WebElement> predicate) -> {
    List<WebElement> elements = driver.findElements(locator).stream().filter(predicate).toList();

    if(!elements.isEmpty()) {
      elements.get(0).click();
    }
  };
}