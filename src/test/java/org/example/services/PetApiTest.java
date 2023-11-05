package org.example.services;


import org.example.assertions.pet.PetAssertions;
import org.example.data.factory.PetDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PetApiTest {

  PetAssertions petAssertions = new PetAssertions();
  PetDataFactory factory;

  @BeforeEach
  public void setUp() {
    factory = new PetDataFactory();
  }

  /*
  Test создает Pet через POST /pet, проверяет что:
    - в ответе присутствует объект Pet;
    - код ответа 200;
    - созданный Pet успешно удален;
   */
  @Test
  public void checkCreatePetWithFullFields() {
    petAssertions.responseShouldBeContainPetObject(factory.createPetWithFullFields());
  }

  @Test
  public void checkCreatePetWithoutRequiredId() {
    petAssertions.responseShouldBeContainPetObject(factory.createPetWithoutId());
  }

}