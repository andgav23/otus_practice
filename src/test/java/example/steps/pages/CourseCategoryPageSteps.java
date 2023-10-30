package example.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import org.example.pages.CourseCategoryPage;
import java.io.IOException;


public class CourseCategoryPageSteps {

  @Inject
  private CourseCategoryPage courseCategoryPage;


  @Если("Сравнить стоимость все курсов каталога выбранной категории")
  public void compareCoursesPrises() throws IOException {
    courseCategoryPage.comparePrice();
  }

  @Тогда("Количество курсов в каталоге должно быть равно количеству цен курсов, полученных для сравнения")
  public void compareCoursesCountAndPricesCount() {
    courseCategoryPage.coursesCountShouldBeEqualsPricesCount();
  }
}
