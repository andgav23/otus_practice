package org.example.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ExercisePage extends AbsBasePage<ExercisePage> {

  public ExercisePage textWidgetExercisePageShouldBe(String text) {
    $(String.format("[text = '%s']", text)).should(Condition.visible);
    return this;
  }

  public ChatPage clickStart() {
    $("[text='Start']").click();
    return new ChatPage();
  }
}
