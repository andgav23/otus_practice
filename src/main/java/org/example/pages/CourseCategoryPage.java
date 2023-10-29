package org.example.pages;

import com.google.inject.Inject;
import org.example.components.CatalogComponent;
import org.example.di.GuiceScoped;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class CourseCategoryPage extends AbsBasePage<CourseCategoryPage> {
  @Inject
  private CatalogComponent catalogComponent;
@Inject
  public CourseCategoryPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @FindBy(xpath = "//h1")
  private WebElement title;


  public void pageTitleShouldBeSameAs(String expectedTitle) {
    assertTrue(standartWaiter.waitForElementVisible(title), String.format("Title %s should be visible", expectedTitle));
    assertTrue(expectedTitle.contains(title.getText()), String.format("Title should be %s", expectedTitle));
  }

  public void findCourse() throws IOException {
    catalogComponent.courses();
  }
}