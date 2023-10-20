package news;

import jdk.jfr.Name;
import org.example.annotations.Driver;
import org.example.components.CoursesComponent;
import org.example.data.SearchFlagsData;
import org.example.extentions.UIExtention;
import org.example.pages.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
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
    MainPage openedPage = new MainPage(driver).open();
    List<String> courseTitles = new CoursesComponent(driver).getCourseTitles();
    openedPage.pageShouldBeContainTitle(courseTitles);

  }

  @Test
  @Name("Открытие самого позднего курса")
  public void latestCourseOpened() {
    new MainPage(driver).open();
    CoursesComponent coursesComponent = new CoursesComponent(driver);
    WebElement latestCourse = coursesComponent
        .getCourseByStartDate(coursesComponent.getCoursesMap(), SearchFlagsData.LATEST);
    String elementTitle = latestCourse.findElement(By.xpath(".//h5")).getText();

    coursesComponent.getActions()
        .moveToElement(latestCourse)
        .build().perform();

    coursesComponent.clickCoursesItem(latestCourse).pageTitleShouldBeSameAs(elementTitle);

  }

  @Test
  @Name("Открытие самого раннего курса")
  public void earliestCourseOpened() {
    new MainPage(driver).open();
    CoursesComponent coursesComponent = new CoursesComponent(driver);
    WebElement earliestCourse = coursesComponent
        .getCourseByStartDate(coursesComponent.getCoursesMap(), SearchFlagsData.EARLIEST);
    String elementTitle = earliestCourse.findElement(By.xpath(".//h5")).getText();

    coursesComponent.getActions()
        .moveToElement(earliestCourse)
        .build().perform();

    coursesComponent.clickCoursesItem(earliestCourse).pageTitleShouldBeSameAs(elementTitle);
  }

}
