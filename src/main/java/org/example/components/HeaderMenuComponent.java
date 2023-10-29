package org.example.components;

import com.google.inject.Inject;
import org.example.annotations.WebComponent;
import org.example.data.SearchFlagsData;
import org.example.di.GuiceScoped;
import org.example.exceptions.ChildElementNotFoundException;
import org.example.pages.CoursePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;


@WebComponent("a ~ nav")
public class HeaderMenuComponent extends AbsComponent<HeaderMenuComponent> {

  @Inject
  public HeaderMenuComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  private String menuItemLocator = "//div[./span[@title='%s']]";
  private String subMenuItemLocator = "//a[contains(text(), '%s')]";

  public void moveToMenuItem(String menuItemData) {
    WebElement menuItemElement = driver.findElement(By.xpath(String.format(menuItemLocator,menuItemData)));
    actions.moveToElement(menuItemElement).build().perform();
  }

  public void checkSubMenuListNotVisible(String menuItemData){
    WebElement menuItemElement = driver.findElement(By.xpath(String.format(menuItemLocator, menuItemData)));
    String classAttr = menuItemElement.getAttribute("class");
    assertTrue(standartWaiter.waitForCondition(driver -> menuItemElement.getAttribute("class").equals(classAttr)));
  }
  public void clickSubMenuItem(String subMenu){
    WebElement subMenuElement = driver.findElement(By.xpath(String.format(subMenuItemLocator,subMenu)));
    subMenuElement.click();
  }
}

