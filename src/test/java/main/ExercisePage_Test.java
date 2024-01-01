package main;

import static org.example.services.StartPageService.closeStartPage;

import org.example.components.MainMenuComponent;
import org.example.data.MainMenuItemData;
import org.example.extensions.AndroidExtensions;
import org.example.pages.ExercisePage;
import org.example.pages.StartPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(AndroidExtensions.class)
public class ExercisePage_Test {
  @BeforeEach
  public void setUp() {
    closeStartPage(new StartPage().open());
  }

  @Test
  public void exerciseStarted() {

    new MainMenuComponent()
        .mainMenuItemIsVisible(MainMenuItemData.EXERCISE)
        .<ExercisePage>openComponentPage(MainMenuItemData.EXERCISE)
        .textWidgetExercisePageShouldBe("Learn 5 new words today")
        .clickStart()
        .editTextWidgetWithTextShouldBeEnable("Type a message...")
        .close();
  }


}
