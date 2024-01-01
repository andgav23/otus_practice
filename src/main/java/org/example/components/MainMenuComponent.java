package org.example.components;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import org.example.data.MainMenuItemData;
import org.example.pages.*;

public class MainMenuComponent {

  public MainMenuComponent mainMenuItemIsVisible(MainMenuItemData mainMenuItemData) {
    $(String.format("[text='%s']", mainMenuItemData.getName())).shouldBe(Condition.visible);
    return this;
  }


  public <T> T openComponentPage(MainMenuItemData menuItem) {
    $(String.format("[text='%s']", menuItem.getName())).click();
    switch (menuItem) {
      case CHAT -> {
        return (T) new ChatPage();
      }
      case EXERCISE -> {
        return (T) new ExercisePage();
      }
      case STATS -> {
        return (T) new StatsPage();
      }
    }

    return (T) this;
  }
}
