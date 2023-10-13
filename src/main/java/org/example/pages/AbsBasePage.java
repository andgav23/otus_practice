package org.example.pages;

import org.example.actions.AbsActions;
import org.example.annotations.Path;
import org.example.annotations.Template;
import org.example.annotations.UrlTemplate;
import org.example.exceptions.PathException;
import org.openqa.selenium.WebDriver;
import java.util.Arrays;

public abstract class AbsBasePage<T> extends AbsActions {

  private final static String BASE_URL = System.getProperty("base.url");


  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  private String getPath(String name, String... data) {
    Class clazz = getClass();

    if (clazz.isAnnotationPresent(UrlTemplate.class)) {
      UrlTemplate urlTemplate = (UrlTemplate) clazz.getAnnotation(UrlTemplate.class);
      Template[] templates = urlTemplate.value();
      Template template = Arrays.stream(templates).filter((Template temp) -> temp.name().equals(name)).findFirst().get();

      String result = template.template();
      for (int i = 0; i < data.length; i++) {
        result = result.replace(String.format("{%d}, i+1"), data[i]);
      }
      return result;
    }
    throw new PathException();

  }

  private String getPath() {
    Class clazz = getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      Path path = (Path) clazz.getAnnotation(Path.class);
      return path.value();
    }
    throw new PathException();
  }

  public T open(String templateName, String... data) {
    driver.get(normalizeBaseUrl() + getPath(templateName, data));
    return (T) this;
  }

  public T open() {
    driver.get(normalizeBaseUrl() + getPath());
    return (T) this;
  }

  private String normalizeBaseUrl() {
    String url = BASE_URL;
    if (!BASE_URL.endsWith("/")) {
      url += "/";
    }
    return url;
  }




}
