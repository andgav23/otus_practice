package example.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import org.example.pages.CourseCategoryPage;

import java.io.IOException;


public class CourseCategoryPageSteps {

  @Inject
  private CourseCategoryPage courseCategoryPage;



  @Если("Сравнить стоимость все курсов каталога выбранной категории")
  public void findAndClickCourse() throws IOException {
    courseCategoryPage.findCourse();
  }

}
