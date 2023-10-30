package org.example.components;

import static org.example.config.SSLConfiguration.getSslContext;

import com.google.inject.Inject;
import org.example.di.GuiceScoped;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class CatalogComponent extends AbsComponent<CatalogComponent> {

  public List<WebElement> getCourses() {
    return courses;
  }

  @FindBy(xpath = "//div/section[2]/div[2]//a[contains(@href, '/lessons/')]")
  private List<WebElement> courses;

  @Inject
  public CatalogComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public int printCoursesWithMaxAndMinPrice() throws IOException {
    List<String> coursesLinks = courses.stream()
        .map(course -> course.getAttribute("href"))
        .toList();
    var coursesMap = getCourseWithPriceMap(getPageDocument(coursesLinks));
    printCourses(getCourseByPrice(coursesMap, "min"), getCourseByPrice(coursesMap, "max"));
    return coursesMap.size();
  }

  private void printCourses(Map.Entry<String, Double> minPriceCourse, Map.Entry<String, Double> maxPriceCourse) {
    System.out.printf(
        "Минимальную стоимость %s руб. имеет курс %s%n", minPriceCourse.getValue(), minPriceCourse.getKey());
    System.out.printf(
        "Максимальную стоимость %s руб. имеет курс %s%n", maxPriceCourse.getValue(), maxPriceCourse.getKey());
  }

  private Map.Entry<String, Double> getCourseByPrice(Map<String, Double> coursesMap, String criteria) {
    switch (criteria) {
      case "max":
        return coursesMap.entrySet().stream().filter(course -> course.getValue() == Collections.max(coursesMap.values()))
            .findFirst().get();

      case "min":
        return coursesMap.entrySet().stream().filter(course -> course.getValue() == Collections.min(coursesMap.values()))
            .findFirst().get();
      default:
        throw new IllegalArgumentException();
    }
  }

  private Map<String, Double> getCourseWithPriceMap(List<Document> pageDocuments) {
    return pageDocuments.stream()
        .collect(Collectors.toMap(
            document -> findTitleElement(document).text(),
            document -> extractPriceFromString(findPriceElement(document).text())));
  }

  private List<Document> getPageDocument(List<String> coursesLinks) {
    SSLContext sslContext = null;
    try {
      sslContext = getSslContext();
    } catch (KeyManagementException | NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    SSLContext finalSslContext = sslContext;
    return coursesLinks.stream().map(link -> {
      try {
        return getConnectionFromLink(link, finalSslContext).get();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }).collect(Collectors.toList());


  }

  private Connection getConnectionFromLink(String link, SSLContext sslContext) {
    return Jsoup.connect(link)
        .sslSocketFactory(sslContext.getSocketFactory())
        .timeout(5000)
        .method(Connection.Method.GET);
  }

  private Element findPriceElement(Document document) {
    return document.getElementsMatchingOwnText("Стоимость в рассрочку").next().get(0).child(0);
  }

  private Element findTitleElement(Document document) {
    return document.select("h1").get(0);
  }


  private Double extractPriceFromString(String input) {

    Pattern pattern = Pattern.compile("\\d+");
    Matcher matcher = pattern.matcher(input);
    List<String> numberStrings = new ArrayList<>();
    while (matcher.find()) {
      numberStrings.add(matcher.group());
    }
    return Double.parseDouble(String.join("", numberStrings));
  }


}

