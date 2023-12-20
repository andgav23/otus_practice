package citrus.cucumber.hooks;

import citrus.services.MqService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks {

  @Autowired
  private MqService mqService;
  @Before
  public void beforeScenario() {
    if (mqService.server != null) {
      mqService.server.start();
    }
  }
  @After
  public void afterScenario() {
    if (mqService.server != null) {
      mqService.server.stop();
    }
  }
}