package org.example.data;

public enum UrlPartsData {
  LESSONS("://otus.ru/lessons/");

  private final String name;

  UrlPartsData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
