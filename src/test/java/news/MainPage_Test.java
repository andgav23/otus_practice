package news;

import org.example.annotations.Driver;
import org.example.components.CoursesComponent;
import org.example.data.CoursesData;
import org.example.extentions.UIExtention;
import org.example.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(UIExtention.class)
public class MainPage_Test {

  @Driver
  private WebDriver driver;


  @Test

  public void mainPageOpened() {
    new MainPage(driver).open();
//    List<String> courseTitles = new CoursesComponent(driver).getCourseTitles();
//    assertThat(courseTitles).contains(CoursesData.SYSTEM_ANALISYS.getName());
    CoursesComponent coursesComponent = new CoursesComponent(driver);
    Map<WebElement, LocalDate> components = coursesComponent.getCoursesMap();
    Actions actions = new Actions(driver);

    actions.moveToElement(coursesComponent.getMaxStartDateCourse(components))
        .perform();


    //coursesComponent.clickCoursesItem(coursesComponent.getMaxStartDateCourse(components));
  }


}
