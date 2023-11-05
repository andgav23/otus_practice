package org.example.assertions.pet;

import static org.junit.jupiter.api.Assertions.*;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.example.client.PetClient;
import org.example.dto.PetDTO;
import org.example.services.ResourceCleaner;

public class PetAssertions {

  PetClient client = new PetClient();
  ResourceCleaner cleaner = new ResourceCleaner(client);


  public void responseShouldBeContainPetObject(PetDTO pet) {
    ValidatableResponse response = client.createPet(pet);
    assertAll("create pet with full fields",
        () -> assertEquals(pet, response.extract().as(PetDTO.class), "Create pet response body should be same as PetDTO"),
        () -> assertEquals(200, response.extract().statusCode(), "Create pet status should be 200"),
        () -> assertEquals(true, cleaner.deletePetIfExist(pet.getId()), "Pet should be deleted"));
  }

  public void petIdShouldBeRequired(PetDTO pet) {
    client.createPet(pet)
        .statusCode(HttpStatus.SC_OK)
        .log().all();
  }

}
