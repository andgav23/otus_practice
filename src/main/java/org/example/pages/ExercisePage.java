package org.example.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

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
