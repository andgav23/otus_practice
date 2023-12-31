package org.example.pages;

import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.Condition;
import org.example.data.MainMenuItemData;
import org.openqa.selenium.By;

public class ChatPage extends AbsBasePage<ChatPage> {

  public ChatPage typeText(String text) {
    $(By.className("android.widget.EditText")).val(text);
    return this;
  }
  public ChatPage clickSendButton() {
    $("[text='Send']").click();
    return this;
  }

  public ChatPage messagesListShouldBeContain(String message) {
    $(String.format("[text='%s']", message)).should(Condition.disabled);
//$(String.format("[text='%s']", message)).click();
    return this;
  }
}
