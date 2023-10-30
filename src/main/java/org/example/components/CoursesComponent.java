package org.example.components;

import com.google.inject.Inject;
import org.example.annotations.WebComponent;
import org.example.data.SearchFlagsData;
import org.example.di.GuiceScoped;
import org.example.exceptions.ChildElementNotFoundException;
import org.example.pages.CoursePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

  @Inject
  public CoursesComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public List<String> getCourseTitles() {
    return courses.stream().map(course -> getWebElementText(course, ".//h5")).collect(Collectors.toList());
  }

  public WebElement findCoursesByName(String courseName) {
    return courses.stream().filter(course -> getWebElementText(course, ".//h5").equals(courseName)).findFirst().get();
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

  public Map<WebElement, LocalDate> findAfterOrEqualsCourseByStartDate(LocalDate startDate) {
    var foundCourses = getCoursesMap().entrySet().stream()
        .filter(course -> course.getValue().compareTo(startDate) >= 0)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    sortAndPrintCourses(foundCourses);
    return foundCourses;
  }

  public void sortAndPrintCourses(Map<WebElement, LocalDate> courses) {
    courses.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .forEach(course ->
            System.out.println(String.format("Курс %s, дата начала %s"
                , getWebElementText(course.getKey(), ".//h5")
                , course.getValue())));


  }

  public WebElement getCourseByStartDate(Map<WebElement, LocalDate> courseMap, SearchFlagsData searchFlag) {

    LocalDate courseStartDate = courseMap.values().stream().reduce((firstDate, lastDate) -> getComparedDates(firstDate, lastDate, searchFlag)).get();

    return courseMap.entrySet().stream().filter(course -> courseStartDate.equals(course.getValue())).findFirst().map(Map.Entry::getKey).get();

  }

  private LocalDate getComparedDates(LocalDate firstDate, LocalDate secondDate, SearchFlagsData searchFlags) {
    switch (searchFlags) {
      case LATEST -> {
        return (firstDate.isAfter(secondDate) ? firstDate : secondDate);
      }
      case EARLIEST -> {
        return (firstDate.isBefore(secondDate) ? firstDate : secondDate);
      }
      default -> {
        throw new IllegalArgumentException();
      }
    }
  }

  public LocalDate getCourseStartDate(WebElement webElement) throws ParseException {
    String datePatternWithoutYear = "\\d{1,2}\\s*(января|февраля|марта|апреля|мая|июня|июля|августа|сентября|октября|ноября|декабря)";
    String datePatternWithYear = datePatternWithoutYear + "\\s*\\d{4}";
    String localDateFormat = "d MMMM yyyy";
    String webElementCourseElement = getWebElementText(webElement, ".//a/div/div/div/div/span[starts-with(text(),\"С \")]");

    String extractedDate = extractDate(webElementCourseElement, datePatternWithYear);

    if (extractedDate == null) {
      extractedDate = extractDate(webElementCourseElement, datePatternWithoutYear);
      return localDateMapper(extractedDate + " " + LocalDate.now().getYear(), localDateFormat);
    }
    return localDateMapper(extractedDate, localDateFormat);

  }

  private String extractDate(String webElementDate, String strPattern) throws ParseException {
    Pattern pattern = Pattern.compile(strPattern);
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

  public String getWebElementText(WebElement el, String courseTitleXPath) {
    try {
      return el.findElement(By.xpath(courseTitleXPath)).getText();
    } catch (NoSuchElementException e) {
      throw new ChildElementNotFoundException(el.getAttribute("class"), courseTitleXPath);
    }
  }

  public CoursePage clickCoursesItem(WebElement courses) {
    courses.click();
    return new CoursePage(guiceScoped);
  }


}

