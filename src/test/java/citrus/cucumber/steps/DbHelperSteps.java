package citrus.cucumber.steps;

import static org.example.utils.JsonBuilder.jsonFromFileToMap;

import citrus.services.DbService;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import org.citrusframework.TestCaseRunner;
import org.citrusframework.annotations.CitrusResource;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

public class DbHelperSteps {
  @CitrusResource
  private TestCaseRunner runner;
  @Autowired
  private DbService dbService;
  @Если("Вставить строку с данными пользователя {string}")
  public void insertUserData(String path) {
    Map<String, Object> citrusVars = jsonFromFileToMap(path);
    for (String key : citrusVars.keySet()) {
      runner.variable(key, citrusVars.get(key));
    }
    dbService.insertRow(runner);
  }

  @Тогда("Таблица содержит одну строк {int}")
  public void tableRowsCountShouldBeEquals(int expectedRowsCount) {
    dbService.checkRowCount(runner, expectedRowsCount);
  }

  @Тогда(("Город пользователя в таблице {string}"))
  public void userCityShouldBeAs(String expectedCity) {
    dbService.checkUserCity(runner, expectedCity);
  }

}
