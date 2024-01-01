package main;

import static org.example.services.StartPageService.closeStartPage;

import org.example.components.MainMenuComponent;
import org.example.data.MainMenuItemData;
import org.example.extensions.AndroidExtensions;
import org.example.pages.ChatPage;
import org.example.pages.StartPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.UUID;


@ExtendWith(AndroidExtensions.class)
public class ChatPage_Test {
  @BeforeEach
  public void setUp() {
    closeStartPage(new StartPage().open());
  }

  @Test
  public void messageSent() {

    String message = UUID.randomUUID().toString();
    new MainMenuComponent()
        .mainMenuItemIsVisible(MainMenuItemData.CHAT)
        .<ChatPage>openComponentPage(MainMenuItemData.CHAT)
        .editTextWidgetWithTextShouldBeEnable("Type a message...")
        .typeText(message)
        .clickSendButton()
        .messagesListShouldBeContain(message)
        .close();
  }


}
