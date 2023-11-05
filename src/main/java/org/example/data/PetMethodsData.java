package org.example.data;

import lombok.Getter;

@Getter
public class PetMethodsData {

  public static final String SERVICE = "/pet";
  public static final String CREATE_PET = String.format("%s/", SERVICE);
  public static final String GET_PET = String.format("%s/{petId}", SERVICE);
  public static final String DELETE_PET = String.format("%s/{petId}", SERVICE);
}
