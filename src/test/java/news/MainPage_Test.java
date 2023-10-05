package news;

import org.example.annotations.Driver;
import org.example.components.CoursesComponent;
import org.example.extentions.UIExtention;
import org.example.pages.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(UIExtention.class)
public class MainPage_Test {

  @Driver
  private WebDriver driver;


  @Test
  public void mainPageOpened() {
    new MainPage(driver).open();
    System.out.println(new CoursesComponent(driver).getCourseTitles());

  }
}
