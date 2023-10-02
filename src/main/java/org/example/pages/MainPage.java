package org.example.pages;

import org.example.annotations.Path;
import org.openqa.selenium.WebDriver;
@Path("/")
public class MainPage extends AbsBasePage<MainPage> {
  public MainPage(WebDriver driver) {
    super(driver);
  }
}
