package org.example.components;

import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.Condition;
import org.example.data.MainMenuItemData;

public class MainMenuComponent {

  public MainMenuComponent mainMenuItemIsVisible(MainMenuItemData mainMenuItemData) {
    $(String.format("[text='%s']", mainMenuItemData.getName())).shouldBe(Condition.visible);
    return this;
  }
}
