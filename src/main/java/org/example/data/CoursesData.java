package org.example.data;

public enum CoursesData {
  SYSTEM_ANALYSIS("Специализация Системный аналитик");

  private final String name;

  CoursesData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
