package org.example.pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.inject.Inject;
import org.example.components.CatalogComponent;
import org.example.di.GuiceScoped;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.io.IOException;


public class CourseCategoryPage extends AbsBasePage<CourseCategoryPage> {
  @Inject
  private CatalogComponent catalogComponent;

  @Inject
  public CourseCategoryPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @FindBy(xpath = "//h1")
  private WebElement title;

  private static int coursesCount;
  private static int priceCount;


  public void pageTitleShouldBeSameAs(String expectedTitle) {
    assertTrue(standartWaiter.waitForElementVisible(title), String.format("Title %s should be visible", expectedTitle));
    assertTrue(expectedTitle.contains(title.getText()), String.format("Title should be %s", expectedTitle));
  }

  public void comparePrice() throws IOException {
    coursesCount = catalogComponent.printCoursesWithMaxAndMinPrice();
    priceCount = catalogComponent.getCourses().size();

  }

  public void coursesCountShouldBeEqualsPricesCount() {
    assertEquals(coursesCount, priceCount);
  }
}