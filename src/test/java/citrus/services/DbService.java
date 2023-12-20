package citrus.services;

import static org.citrusframework.actions.ExecuteSQLAction.Builder.sql;
import static org.citrusframework.actions.ExecuteSQLQueryAction.Builder.query;

import lombok.extern.slf4j.Slf4j;
import org.citrusframework.TestCaseRunner;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;

@Slf4j
public class DbService {

  @Autowired
  private DataSource dbDataSource;

  public void insertRow(TestCaseRunner runner) {
    runner.$(sql(dbDataSource)
        .statement("insert into users values ('${id}', '${name}', '${school_name}', '${city}', ${grade})"));
  }

  public void checkRowCount(TestCaseRunner runner, int rowCount) {
    runner.$(query(dbDataSource)
        .statement("select count(*) as cnt from users")
        .validate("cnt", String.valueOf(rowCount)));
  }

  public void checkUserCity(TestCaseRunner runner, String expectedCity) {
    runner.$(query(dbDataSource)
        .statement("select city from users")
        .validate("city", expectedCity));
  }
}
