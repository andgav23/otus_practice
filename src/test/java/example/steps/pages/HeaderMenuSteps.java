package example.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.example.components.HeaderMenuComponent;
import org.example.pages.CourseCategoryPage;
import org.example.pages.MainPage;


public class HeaderMenuSteps {

  @Inject
  private HeaderMenuComponent headerMenuComponent;
  @Inject
  private CourseCategoryPage courseCategoryPage;

  @Пусть("Пункт главного меню {string} закрыт")
  public void menuItemShouldNotBeVisible(String menuItem){
    headerMenuComponent.checkSubMenuListNotVisible(menuItem);
  }

  @Если("Открыть пункт главного меню {string}")
  public void openMainMenuItem(String menuItem){
    headerMenuComponent.moveToMenuItem(menuItem);
  }

  @Если ("Открыть категорию {string}")
    public void openCategory (String categoryName){
    headerMenuComponent.clickSubMenuItem(categoryName);
  }

  @Тогда ("Открыта страница {string}")
    public void pageCatalogShouldBeOpened(String pageTitle) {
    courseCategoryPage.pageTitleShouldBeSameAs(pageTitle);
  }


}
