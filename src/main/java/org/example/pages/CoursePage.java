package org.example.pages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.inject.Inject;
import org.example.di.GuiceScoped;
import org.openqa.selenium.WebDriver;


public class CoursePage extends AbsBasePage<CoursePage> {
@Inject
  public CoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public void pageTitleShouldBeSameAs(String title) {

    assertTrue(title.contains(this.title.getText()));
  }
}