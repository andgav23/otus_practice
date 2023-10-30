package org.example.pages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.inject.Inject;
import org.example.di.GuiceScoped;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CoursePage extends AbsBasePage<CoursePage> {
  @Inject
  public CoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @FindBy(xpath = "//h1")
  private WebElement title;

  public void pageTitleShouldBeSameAs(String expectedTitle) {
    assertTrue(standartWaiter.waitForElementVisible(title), String.format("Title %s should be visible", expectedTitle));
    assertTrue(expectedTitle.contains(title.getText()), String.format("Title should be %s", expectedTitle));
  }
}