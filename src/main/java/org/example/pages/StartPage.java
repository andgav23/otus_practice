package org.example.pages;

import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.Condition;
import org.example.components.AlertComponent;

public class StartPage extends AbsBasePage<StartPage> {

  public StartPage textWidgetStartPageShouldBe(String text) {
    $(String.format("[text = '%s']", text)).should(Condition.visible);
    return this;
  }

  public StartPage clickNext() {
    $("[text='Next']").click();
    return this;
  }

  public AlertComponent clickSkipButton() {
    $("[text='Skip >']").click();
    return new AlertComponent();
  }

  public boolean isOpened() {
    return $("[text='Next']").has(Condition.visible);
  }
}
