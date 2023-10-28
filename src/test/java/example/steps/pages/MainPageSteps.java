package example.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import org.example.pages.MainPage;

public class MainPageSteps {
  @Inject
  private MainPage mainPage;

  @Пусть("Открыта главная страница")
  public void openMainPage(){
    mainPage.open();
  }

  @Если("Найти и кликнуть курс {string}")
  public void findAndClickCourse(String courseName){
    mainPage.clickCourseByName(courseName);
  }
}
