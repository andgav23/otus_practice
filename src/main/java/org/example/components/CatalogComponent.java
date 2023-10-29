package org.example.components;

import com.google.inject.Inject;
import org.example.annotations.WebComponent;
import org.example.data.SearchFlagsData;
import org.example.di.GuiceScoped;
import org.example.exceptions.ChildElementNotFoundException;
import org.example.pages.CoursePage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import us.codecraft.xsoup.Xsoup;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.example.config.SSLConfiguration.getSslContext;


public class CatalogComponent extends AbsComponent<CatalogComponent> {

  @FindBy(xpath = "//div/section[2]/div[2]//a[contains(@href, '/lessons/')]")
  private List<WebElement> courses;

  @Inject
  public CatalogComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public void courses() throws IOException {
    courses.stream().forEach(course-> {
      System.out.println(course.getAttribute("href"));
      try {
        getPrice(course.getAttribute("href"));
      } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
      } catch (KeyManagementException | IOException e) {
        throw new RuntimeException(e);
      }
    });

  }

  private void getPrice(String courseLink) throws NoSuchAlgorithmException, KeyManagementException, IOException {
    SSLContext sslContext = getSslContext();
    Connection connection = Jsoup.connect(courseLink)
        .sslSocketFactory(sslContext.getSocketFactory())
        .timeout(5000)
        .method(Connection.Method.GET);

    //Document doc = Jsoup.connect(coursLink).timeout(10000).get();
 Element element = connection.get().getElementsMatchingOwnText("Стоимость в рассрочку").next().get(0).child(0);
    System.out.println(Double.parseDouble(extractIntegers(element.text())));
//.select("p:contains()+div")

  }

  public static String extractIntegers(String input) {
    Pattern pattern = Pattern.compile("\\d+");
    Matcher matcher = pattern.matcher(input);

    List<String> numberStrings = new ArrayList<>();
    while (matcher.find()) {
      numberStrings.add(matcher.group());
    }



    return String.join("", numberStrings);
  }





}

