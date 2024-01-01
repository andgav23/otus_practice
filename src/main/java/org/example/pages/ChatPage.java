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
  public ChatPage editTextWidgetWithTextShouldBeEnable(String text) {
    $(String.format("[text = '%s']", text)).should(Condition.enabled);
    return this;
  }
  public ChatPage clickSendButton() {
    $("[text='Send']").click();
    return this;
  }

  public ChatPage messagesListShouldBeContain(String message) {
$(String.format("[text='%s%s']", message, " ")).should(Condition.visible);
    return this;
  }
}
