package main;

import static org.example.services.StartPageService.closeStartPage;

import org.example.components.MainMenuComponent;
import org.example.data.MainMenuItemData;
import org.example.extensions.AndroidExtensions;
import org.example.pages.StartPage;
import org.example.pages.StatsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(AndroidExtensions.class)
public class StatsPage_Test {
  @BeforeEach
  public void setUp() {
    closeStartPage(new StartPage().open());
  }

  @Test
  public void languageIsExist() {

    new MainMenuComponent()
        .mainMenuItemIsVisible(MainMenuItemData.STATS)
        .<StatsPage>openComponentPage(MainMenuItemData.STATS)
        .textWidgetStatsPageShouldBe("Settings")
        .clickButton("Settings")
        .textWidgetStatsPageShouldBe("Translations")
        .clickButton("Translations")
        .scrollTo("French")
        .clickButton("French")
        .textWidgetStatsPageShouldBe("Translations")
        .close();
  }


}
