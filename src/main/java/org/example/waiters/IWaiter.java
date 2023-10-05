package org.example.waiters;

import org.openqa.selenium.support.ui.ExpectedCondition;

public interface IWaiter {
  long IMPLICITLY_WAIT_SECOND = Integer.parseInt(
      System.getProperty("webdriver.timeouts.implicitlywait", "10000")) / 1000;

  boolean waitForCondition(ExpectedCondition condition);
}
