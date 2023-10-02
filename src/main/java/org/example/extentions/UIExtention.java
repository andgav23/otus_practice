package org.example.extentions;

import org.example.annotations.Driver;
import org.example.factory.FactoryWebDriver;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

public class UIExtention implements BeforeEachCallback, AfterEachCallback {
  private WebDriver driver;

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
    // TODO добавить обертку 31/24 минута до конца первой практики
    driver = new FactoryWebDriver().create();
    List<Field> fields = getAnnotatedFields(Driver.class, extensionContext);

    for (Field field : fields) {
      if (field.getType().getName().equals(WebDriver.class.getName())) {
        AccessController.doPrivileged((PrivilegedAction<Void>)
            () -> {
              try {
                field.setAccessible(true);
                field.set(extensionContext.getTestInstance().get(), driver);
              } catch (IllegalAccessException e) {
                throw new Error(String.format("Could not access or set webdriver in field: %s - is this field public?", field.getName()));
              }
              return null;
            }
        );
      }
    }
  }


  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    if (driver != null) {
      driver.close();
      driver.quit();
    }
  }
}
