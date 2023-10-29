package example.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.example.pages.MainPage;


public class MainPageSteps {

  @Inject
  private MainPage mainPage;



  @Если("Найти и кликнуть курс {string}")
  public void findAndClickCourse(String courseName) {
    mainPage.clickCourseByName(courseName);
  }

  @Тогда("Откроется страница курса {string}")
  public void  coursePageOpens(String courseName){
    mainPage.courseTitleShouldBeSameAs(courseName);
  }

  @Если("Найти курсы стартующие {string} или позднее")
  public void findAfterOrEqualsCourseByStartDate(String startDate) {
    mainPage.findCoursesByDate(startDate);
  }

  @Тогда("Дата самого раннего найденного курса больше или равна {string}")
  public void dateEarliestCourseEqualsOrGreather(String startDate) {
    mainPage.startDateShouldBeEqualsOrGreater(startDate);
  }

  @Пусть("Открыта главная страница")
  public void openMainPage() {
    mainPage.open();
  }
}
