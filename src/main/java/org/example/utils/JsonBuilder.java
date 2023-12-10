package org.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
@Slf4j
public class JsonBuilder {
  public JSONObject jsonObject;
  private String jsonFilePath;

  public JSONObject getJsonObject() {
    return jsonObject;
  }

  public JsonBuilder setJsonObject(String path) {
    jsonFilePath = path;
    jsonObject = readJsonAsObject();
    return this;
  }

  public JSONObject readJsonAsObject() {
    InputStream fileInputStream = null;
    log.info(jsonFilePath);
    fileInputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath);
    JSONTokener jsonTokener = new JSONTokener(fileInputStream);
    jsonObject = new JSONObject(jsonTokener);
    return jsonObject;
  }

}