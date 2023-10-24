package org.example.pages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.inject.Guice;
import com.google.inject.Inject;
import org.example.annotations.Path;
import org.example.data.CoursesData;
import org.example.di.GuiceScoped;
import org.openqa.selenium.WebDriver;
import java.util.List;



@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

@Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }
  public void pageShouldBeContainTitle(List<String> courseTitles) {

    assertTrue(courseTitles.contains(CoursesData.SYSTEM_ANALYSIS.getName()));
  }

}