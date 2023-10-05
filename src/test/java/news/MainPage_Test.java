package news;

import org.example.annotations.Driver;
import org.example.components.CoursesComponent;
import org.example.data.CoursesData;
import org.example.extentions.UIExtention;
import org.example.pages.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(UIExtention.class)
public class MainPage_Test {

  @Driver
  private WebDriver driver;


  @Test
  public void courseFound() {
    new MainPage(driver).open();
    List<String> courseTitles = new CoursesComponent(driver).getCourseTitles();
    assertThat(courseTitles).contains(CoursesData.SYSTEM_ANALISYS.getName());

  }

  @Test
  public void latestCourseOpened () {
    new MainPage(driver).open();
    CoursesComponent coursesComponent = new CoursesComponent(driver);
    WebElement latestCourse = coursesComponent.getMaxStartDateCourse(coursesComponent.getCoursesMap());
    Actions actions = new Actions(driver);

    actions
        .moveToElement(latestCourse)
        .pause(5000)
        .build().perform();

    coursesComponent.clickCoursesItem(latestCourse);
  }


}
