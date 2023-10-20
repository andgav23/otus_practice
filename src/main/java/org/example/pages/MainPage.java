package org.example.pages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.annotations.Path;
import org.example.data.CoursesData;
import org.openqa.selenium.WebDriver;
import java.util.List;



@Path("/")
public class MainPage extends AbsBasePage<MainPage> {


  public MainPage(WebDriver driver) {
    super(driver);
  }
  public void pageShouldBeContainTitle(List<String> courseTitles) {

    assertTrue(courseTitles.contains(CoursesData.SYSTEM_ANALYSIS.getName()));
  }

}