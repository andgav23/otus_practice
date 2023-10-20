package org.example.pages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;


public class CoursePage extends AbsBasePage<CoursePage> {

  public CoursePage(WebDriver driver) {
    super(driver);
  }

  public void pageTitleShouldBeSameAs(String title) {

    assertTrue(title.contains(this.title.getText()));
  }
}