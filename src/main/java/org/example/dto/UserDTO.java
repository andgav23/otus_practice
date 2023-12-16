package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
  String id;
  String name;
  String school_name;
  String sity;
  int grade;
}
