package org.example.components;

import org.example.annotations.WebComponent;
import org.example.exceptions.ChildElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@WebComponent("//*[@id=\"__next\"]/div[1]/main/div/div/div/div[1]/a/div/div/div")
public class CoursesComponent extends AbsComponent<CoursesComponent> {
  @FindBy(xpath = "//*[@id='__next']/*/main/div/*/div/*")
  private List<WebElement> courses;

  public CoursesComponent(WebDriver driver) {
    super(driver);
  }

  public List<String> getCourseTitles() {
    return courses.stream().map(cource -> getWebElementText(cource, ".//h5")).collect(Collectors.toList());
  }

  public Map<WebElement, LocalDate> getCoursesMap() {
    Map<WebElement, LocalDate> coursesMap = new HashMap<>();
    for (WebElement course : courses) {
      try {
        coursesMap.put(course, getCourseStartDate(course));
      } catch (ParseException e) {
        throw new RuntimeException(e);
      }
    }
    return coursesMap;
  }

  public WebElement getMaxStartDateCourse(Map<WebElement, LocalDate> courseMap) {
    LocalDate max = Collections.max(courseMap.values());
    return courseMap.entrySet().stream()
        .filter(course -> course.getValue() == max)
        .map(course -> course.getKey())
        .toList().get(0);
  }

  public LocalDate getCourseStartDate(WebElement webElement) throws ParseException {
    String datePatternWithoutYear =
        "\\d{1,2}\\s*(января|февраля|марта|апреля|мая|июня|июля|августа|сентября|октября|ноября|декабря)";
    String datePatternWithYear = datePatternWithoutYear + "\\s*\\d{4}";
    String localDateFormat = "d MMMM yyyy";
    String webElementCourseElement = getWebElementText(webElement, ".//a/div/div/div/div/span[starts-with(text(),\"С \")]");

    String extractedDate = extractDate(webElementCourseElement, datePatternWithYear);

    if (extractedDate == null) {
      extractedDate = extractDate(webElementCourseElement, datePatternWithoutYear);
      return localDateMapper(extractedDate + " " + LocalDate.now().getYear(), localDateFormat);
    }
    return
        localDateMapper(extractedDate, localDateFormat);

  }

  private String extractDate(String webElementDate, String strPattern) throws ParseException {
    Pattern pattern
        = Pattern.compile(strPattern);
    Matcher matcher = pattern.matcher(webElementDate);
    String strDate = null;
    while (matcher.find()) {
      strDate = matcher.group();
    }
    return strDate;
  }

  private LocalDate localDateMapper(String strDate, String datePattern) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern, Locale.forLanguageTag("ru"));
    return LocalDate.parse(strDate, formatter);
  }

  private String getWebElementText(WebElement el, String courseTitleXPath) {
    try {
      return el.findElement(By.xpath(courseTitleXPath)).getText();
    } catch (NoSuchElementException e) {
      throw new ChildElementNotFoundException(el.getAttribute("class"), courseTitleXPath);
    }
  }

  public void clickCoursesItem(WebElement courses) {
    courses.click();

  }

}

