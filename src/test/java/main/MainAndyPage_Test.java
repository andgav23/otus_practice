package main;

import static org.example.services.MainPageService.closeStartPage;
import org.example.components.MainMenuComponent;
import org.example.data.MainMenuItemData;
import org.example.extensions.AndroidExtensions;
import org.example.pages.StartPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(AndroidExtensions.class)
public class MainAndyPage_Test {
  @BeforeEach
  public void setUp() {
    closeStartPage(new StartPage().open());
  }

  @Test
  public void menuButtons() {
//    StartPage startPage = new StartPage().open();
//    if (startPage.isStartPageOpened()) {
//      new StartPage()
//          .textWidgetStartPageShouldBe("Chat to improve your English")
//          .clickNext()
//          .textWidgetStartPageShouldBe("Learn new words and grammar")
//          .clickNext()
//          .textWidgetStartPageShouldBe("7 days FREE")
//          .clickSkipButton();
//    }

    new MainMenuComponent()
        .mainMenuItemIsVisible(MainMenuItemData.CHAT)
        .mainMenuItemIsVisible(MainMenuItemData.EXERCISE)
        .mainMenuItemIsVisible(MainMenuItemData.GRAMMAR)
        .mainMenuItemIsVisible(MainMenuItemData.STATS);
  }

}
