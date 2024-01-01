package org.example.services;

import org.example.pages.StartPage;

public class StartPageService {

  public static void closeStartPage(StartPage startPage) {
    if (startPage.isOpened()) {
      startPage
          .textWidgetStartPageShouldBe("Chat to improve your English")
          .clickNext()
          .textWidgetStartPageShouldBe("Learn new words and grammar")
          .clickNext()
          .textWidgetStartPageShouldBe("7 days FREE")
          .clickSkipButton()
          .clickOkButton();
    }
  }


}
