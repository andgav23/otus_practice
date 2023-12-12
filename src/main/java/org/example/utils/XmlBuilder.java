package org.example.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.nio.charset.Charset;

public class XmlBuilder {
  public static String xmlFromFile(String path) {
    try {
      return StreamUtils.copyToString(new ClassPathResource(path).getInputStream(), Charset.defaultCharset());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
