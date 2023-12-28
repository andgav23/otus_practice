package org.example.services;

import org.example.pages.MainPage;
import org.example.pages.StartPage;

public class MainPageService {
  public static MainPage closeStartPage(StartPage startPage){
    return startPage
        .textWidgetStartPageShouldBe("Chat to improve your English")
          .clickNext()
          .textWidgetStartPageShouldBe("Learn new words and grammar")
          .clickNext()
          .textWidgetStartPageShouldBe("7 days FREE")
          .clickSkipButton();
  }
}
