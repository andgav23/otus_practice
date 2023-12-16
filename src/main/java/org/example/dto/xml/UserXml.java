package org.example.dto.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserXml {
  @XmlElement(name = "id")
  public String id;
  @XmlElement(name = "name")
  public String name;
  @XmlElement(name = "school_name")
  public String schoolName;
  @XmlElement(name = "city")
  public String city;
  @XmlElement(name = "grade")
  public int grade;

}
