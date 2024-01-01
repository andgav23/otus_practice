package org.example.pages;

import com.codeborne.selenide.Selenide;

public abstract class AbsBasePage<T> {

  public T open() {
    Selenide.open();
    return (T) this;
  }

  public T close() {
    Selenide.closeWebDriver();
    return (T) this;
  }
}
