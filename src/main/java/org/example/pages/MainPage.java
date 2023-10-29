package org.example.pages;

import com.google.inject.Inject;
import org.example.annotations.Path;
import org.example.components.CoursesComponent;
import org.example.di.GuiceScoped;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;


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

  private static Map<WebElement, LocalDate> checkedCourses = new HashMap<>();

  public void clickCourseByName(String courseName) {
    WebElement course = coursesComponent.findCoursesByName(courseName);
    coursesComponent.clickCoursesItem(course);
  }

  public void courseTitleShouldBeSameAs(String courseName) {
    coursePage.pageTitleShouldBeSameAs(courseName);
  }


  public void findCoursesByDate(String strStartDate) {
    LocalDate startDate = LocalDate.parse(strStartDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    checkedCourses.putAll(coursesComponent.findAfterOrEqualsCourseByStartDate(startDate));

  }

  public void startDateShouldBeEqualsOrGreater(String strStartDate) {
    LocalDate startDate = LocalDate.parse(strStartDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    LocalDate minStartDate = Collections.min(checkedCourses.values());

    assertTrue(String.format("Course start date should be equals or greather %s", startDate),minStartDate.compareTo(startDate) >= 0);
  }


}



