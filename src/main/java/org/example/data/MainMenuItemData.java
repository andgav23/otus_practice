package org.example.data;

public enum MainMenuItemData {
  CHAT("Chat"),
  EXERCISE("Exercise"),
  STATS("Stats"),
  GRAMMAR("Grammar");
  private String name;


  MainMenuItemData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}

