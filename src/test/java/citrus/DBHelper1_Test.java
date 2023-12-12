package citrus;

import static org.citrusframework.actions.ExecuteSQLQueryAction.Builder.query;
import org.citrusframework.TestActionRunner;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.annotations.CitrusTest;
import org.citrusframework.config.CitrusSpringConfig;
import org.citrusframework.junit.jupiter.spring.CitrusSpringSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import javax.sql.DataSource;

@CitrusSpringSupport
@ContextConfiguration(classes = CitrusSpringConfig.class)
public class DBHelper1_Test {
  @Autowired
  private DataSource helperDataSource;

  @Test
  @CitrusTest
  public void tableShouldBeContainRows(@CitrusResource TestActionRunner actions) {
    actions.$(query(helperDataSource)
        .statement("select count(*) as cnt from otus_students")
        .validate("cnt", "1"));
  }
}
