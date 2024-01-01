package org.example.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class StatsPage extends AbsBasePage<StatsPage> {

  public StatsPage textWidgetStatsPageShouldBe(String text) {
    $(String.format("[text = '%s']", text)).should(Condition.visible);
    return this;
  }

  public StatsPage clickButton(String text) {
    $(String.format("[text = '%s']", text)).click();
    return this;
  }

  public StatsPage scrollTo(String text) {
    $(String.format("[text = '%s']", text)).scrollTo();
    return this;
  }


}
