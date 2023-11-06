package org.example.assertions.pet;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.example.client.PetClient;
import org.example.dto.PetDTO;
import org.example.services.PetService;

public class PetAssertions {

  PetClient client = new PetClient();
  PetService petService = new PetService(client);


  public void responseShouldBeContainPetObject(PetDTO pet) {
    ValidatableResponse response = client.createPet(pet);
    PetDTO createdPet = response.extract().as(PetDTO.class);
    pet.setId(createdPet.getId());
    assertAll("create pet",
        () -> assertEquals(pet, createdPet, "Created pet response body should be same as PetDTO"),
        () -> assertEquals(HttpStatus.SC_OK, response.extract().statusCode(), "Create pet status should be 200"),
        () -> assertTrue(petService.deletePetIfExist(createdPet.getId()), "Pet should be deleted"));
  }

  public void petShouldBeSameAsCreated(PetDTO pet) {
    pet.setId(client.createPet(pet).extract().as(PetDTO.class).getId());
    ValidatableResponse response = client.getPet(pet.getId());
    assertTrue(petService.deletePetIfExist(pet.getId()), "Pet should be deleted");
    response
        .assertThat()
        .statusCode(HttpStatus.SC_OK)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/Pet.json"));
  }

  public void petShouldBeNotFound(long petId) {
    client.getPet(petId)
        .assertThat()
        .statusCode(HttpStatus.SC_NOT_FOUND)
        .body("message", equalTo("Pet not found"));
  }

}
