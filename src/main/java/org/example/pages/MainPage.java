package org.example.pages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.inject.Inject;
import org.example.annotations.Path;
import org.example.components.CoursesComponent;
import org.example.data.CoursesData;
import org.example.data.SearchFlagsData;
import org.example.di.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;



@Path("/")
public class MainPage extends AbsBasePage<MainPage> {
  @Inject
  private CoursesComponent coursesComponent;
  @Inject
  private CoursePage coursePage;
@Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }



//  public void pageShouldBeContainCourseComponent(CoursesData coursesData) {
//  this.open();
//    List<String> courseTitles = courseComponent.getCourseTitles();
//    System.out.println(courseTitles);
//
//    //assertTrue(courseTitles.contains(CoursesData.SYSTEM_ANALYSIS.getName()));
//  }

  public void clickCourseByName(String courseName) {
    WebElement course = coursesComponent.findCoursesByName(courseName);
    coursesComponent.clickCoursesItem(course);
    coursePage.pageTitleShouldBeSameAs(courseName);
  }


}