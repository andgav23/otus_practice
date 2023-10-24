package org.example.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.inject.Inject;
import org.example.di.GuiceScoped;
import org.example.waiters.StandartWaiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public abstract class AbsActions<T> {


  protected WebDriver driver;
  protected StandartWaiter standartWaiter;
  protected Actions actions;
  protected GuiceScoped guiceScoped;

  @FindBy(tagName = "h1")
  protected WebElement title;
@Inject
  public AbsActions(GuiceScoped guiceScoped) {
  this.guiceScoped = guiceScoped;
    this.driver = guiceScoped.driver;
    this.actions = new Actions(driver);
    PageFactory.initElements(driver, this);
    standartWaiter = new StandartWaiter(driver);
  }

  protected BiConsumer<By, Predicate<? super WebElement>> clickElementByPredicate = (By locator, Predicate<? super WebElement> predicate) -> {
    List<WebElement> elements = driver.findElements(locator).stream().filter(predicate).toList();

    if (!elements.isEmpty()) {
      elements.get(0).click();
    }
  };



}
