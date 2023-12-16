package org.example.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lombok.SneakyThrows;
import org.example.dto.SomeXmlDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import java.io.StringWriter;
import java.nio.charset.Charset;

public class XmlBuilder {
  @SneakyThrows
  public static String xmlFromFile(String path) {
      return StreamUtils.copyToString(new ClassPathResource(path).getInputStream(), Charset.defaultCharset());

  }

//  @SneakyThrows
//  public static String someXmlAsString() {
//    JAXBContext context = JAXBContext.newInstance(SomeXmlDTO.class);
//    StringWriter sw = new StringWriter();
//    Marshaller marshaller = context.createMarshaller();
//    marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
//    marshaller.marshal(new SomeXmlDTO(),sw);
//    return sw.toString();
//  }
}
