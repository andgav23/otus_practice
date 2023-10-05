package org.example.extentions;

import org.example.annotations.Driver;
import org.example.factory.impl.WebDriverFactory;
import org.example.listeners.MouseListener;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UIExtention implements BeforeEachCallback, AfterEachCallback {
  private EventFiringWebDriver driver = null;
  private static final Logger LOGGER = LoggerFactory.getLogger(UIExtention.class);

  private List<Field> getAnnotatedFields(Class<? extends Annotation> annotaion, ExtensionContext extensionContext) {
    List<Field> set = new ArrayList<>();
    Class<?> testClass = extensionContext.getTestClass().get();
    while (testClass != null) {
      for (Field field : testClass.getDeclaredFields()) {
        if (field.isAnnotationPresent(annotaion)) {
          set.add(field);
        }
      }
      testClass = testClass.getSuperclass(); // если вдруг тест экстендится от другого класса, что не рекомендует JUnit
    }
    return set;
  }

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    driver = new WebDriverFactory().create();
    driver.register(new MouseListener());
    List<Field> fields = getAnnotatedFields(Driver.class, extensionContext);

    for (Field field : fields) {
      if (field.getType().getName().equals(WebDriver.class.getName())) {
        try {
          field.setAccessible(true);
          field.set(extensionContext.getTestInstance().get(), driver);
        } catch (IllegalAccessException e) {
          throw new Error(String.format("Could not access or set webdriver in field: %s", field.getName()));
        }
      }
    }
  }


  @Override
  public void afterEach(ExtensionContext extensionContext) {
    if (driver != null) {
      driver.close();
      LOGGER.info("Driver closed");
      driver.quit();
    }
  }
}
