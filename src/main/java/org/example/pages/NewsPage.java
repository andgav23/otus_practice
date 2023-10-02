package org.example.pages;

import org.example.annotations.Template;
import org.example.annotations.UrlTemplate;
import org.openqa.selenium.WebDriver;

@UrlTemplate({@Template(name = "page1", template = "/{1}/{2}"),@Template(name = "page2", template = "/{1}/news/{2}")})
public class NewsPage extends AbsBasePage<NewsPage> {
  public NewsPage(WebDriver driver, String path) {
    super(driver);
  }
}
