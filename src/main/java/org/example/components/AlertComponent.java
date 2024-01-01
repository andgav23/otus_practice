package org.example.components;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

public class AlertComponent {
  public void clickOkButton() {
    if (isAlertOpened()) {
      $("[text='OK']").click();
    }
  }

  public boolean isAlertOpened() {
    return $(By.id("android:id/alertTitle")).has(Condition.visible);
  }
}
