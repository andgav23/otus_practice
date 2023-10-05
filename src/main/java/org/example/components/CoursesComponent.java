package org.example.components;

import org.example.annotations.WebComponent;
import org.example.exceptions.ChildElementNotFoundException;
import org.example.extentions.UIExtention;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;


@WebComponent("//*[@id=\"__next\"]/div[1]/main/div")
public class CoursesComponent extends AbsComponent<CoursesComponent> {
  private final String courseTitleXPath = ".//h5";
  @FindBy(xpath = "//*[@id='__next']/*/main/div/*/div/*")
  private List<WebElement> courses;

  public CoursesComponent(WebDriver driver) {
    super(driver);
  }

  public List<String> getCourseTitles() {

    return courses.stream().map(el -> getWebElementText(el)).collect(Collectors.toList());

  }

  private String getWebElementText(WebElement el) {
    try {
      return el.findElement(By.xpath(courseTitleXPath)).getText();
    } catch (NoSuchElementException e) {
      throw new ChildElementNotFoundException(el.getAttribute("class"), courseTitleXPath);
    }
  }
  //  public void clickCoursesItem() {
  //    courses.get(0).click();
  //
  //  }

}

