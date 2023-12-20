package org.example.utils;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import java.nio.charset.Charset;

public class XmlBuilder {
  @SneakyThrows
  public static String xmlFromFile(String path) {
      return StreamUtils.copyToString(new ClassPathResource(path).getInputStream(), Charset.defaultCharset());

  }

}
