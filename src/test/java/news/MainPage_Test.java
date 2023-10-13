package news;

import static org.assertj.core.api.Assertions.assertThat;

import jdk.jfr.Name;
import org.example.annotations.Driver;
import org.example.components.CoursesComponent;
import org.example.data.CoursesData;
import org.example.data.SearchFlagsData;
import org.example.data.UrlPartsData;
import org.example.extentions.UIExtention;
import org.example.pages.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;


@ExtendWith(UIExtention.class)
public class MainPage_Test {

  @Driver
  private WebDriver driver;


  @Test
  @Name("Поиск курса \"Специализация Системный аналитик\"")
  public void courseFound() {
    new MainPage(driver).open();
    List<String> courseTitles = new CoursesComponent(driver).getCourseTitles();
    assertThat(courseTitles).contains(CoursesData.SYSTEM_ANALYSIS.getName());

  }

  @Test
  @Name("Открытие самого позднего курса")
  public void latestCourseOpened() {
    new MainPage(driver).open();
    CoursesComponent coursesComponent = new CoursesComponent(driver);
    WebElement latestCourse = coursesComponent
        .getCourseByStartDate(coursesComponent.getCoursesMap(), SearchFlagsData.LATEST);

    coursesComponent.getActions()
        .moveToElement(latestCourse)
        .build().perform();

    coursesComponent.clickCoursesItem(latestCourse);
    assertThat(driver.getCurrentUrl()).contains(UrlPartsData.LESSONS.getName());

  }

  @Test
  @Name("Открытие самого раннего курса")
  public void earliestCourseOpened() {
    new MainPage(driver).open();
    CoursesComponent coursesComponent = new CoursesComponent(driver);
    WebElement earliestCourse = coursesComponent
        .getCourseByStartDate(coursesComponent.getCoursesMap(), SearchFlagsData.EARLIEST);

    coursesComponent.getActions()
        .moveToElement(earliestCourse)
        .build().perform();

    coursesComponent.clickCoursesItem(earliestCourse);
    assertThat(driver.getCurrentUrl()).contains(UrlPartsData.LESSONS.getName());

  }


}
