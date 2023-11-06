package org.example.services;


import jdk.jfr.Name;
import org.example.assertions.pet.PetAssertions;
import org.example.data.factory.PetDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class PetApi_Test {

  PetAssertions petAssertions = new PetAssertions();
  PetDataFactory factory;

  @BeforeEach
  public void setUp() {
    factory = new PetDataFactory();
  }

  /*
  Тест создает Pet со всеми полями через POST /pet , проверяет что:
    - в ответе присутствует объект Pet;
    - код ответа 200;
    - созданный Pet успешно удален;
   */
  @Test
  @DisplayName("Проверка создания Pet со всеми заполненными полями")
  public void checkCreatePetWithFullFields() {
    petAssertions.responseShouldBeContainPetObject(factory.createPetWithFullFields());
  }

  /*
  Тест создает Pet с пустыми полями через POST /pet , проверяет что:
    - в ответе присутствует объект Pet;
    - код ответа 200;
    - созданный Pet успешно удален;
   */
  @Test
  @DisplayName("Проверка создания Pet со всеми пустыми полями")
  public void checkCreateEmptyPet() {
    petAssertions.responseShouldBeContainPetObject(factory.createEmptyPet());
  }

  /*
  Тест создает Pet с пустыми полями через POST /pet, получает его через GET /pet/{petId} и проверяет что:
  - в ответе присутствует объект Pet;
  - код ответа 200;
  - созданный Pet успешно удален;
 */
  @Test
  @DisplayName("Проверка получения существующего Pet")
  public void checkGetExistingPet() {
    petAssertions.petShouldBeSameAsCreated(factory.createPetWithFullFields());
  }

  /*
  Тест получает несуществующий Pet через GET /pet/{petId} и проверяет что:
  - в ответе присутствует объект Pet;
  - код ответа 404;
  - содержимое поля "message" тела ответа;
 */
  @Test
  @DisplayName("Проверка получения несуществующего Pet")
  public void checkGetNotExistingPet() {
    petAssertions.petShouldBeNotFound(factory.getNotExistingPetId());
  }
}