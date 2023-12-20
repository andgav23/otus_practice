package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

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
  @SneakyThrows
  public static String jsonFromFileToString(String path) {
    return StreamUtils.copyToString(new ClassPathResource(path).getInputStream(), Charset.defaultCharset());
  }
  @SneakyThrows
  public static Map<String, Object> jsonFromFileToMap(String path) {
    return new ObjectMapper().readValue(new ClassPathResource(path).getInputStream(), HashMap.class);
  }

}
