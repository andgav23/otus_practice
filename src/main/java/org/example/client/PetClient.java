package org.example.client;

import static io.restassured.RestAssured.given;
import static org.example.data.PetMethodsData.*;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.dto.PetDTO;
import org.example.specs.pet.CreatePetSpec;
import org.example.specs.pet.DeletePetSpec;
import org.example.specs.pet.GetPetSpec;

public class PetClient {



  public ValidatableResponse createPet(PetDTO pet) {
   return given(new CreatePetSpec().getRequestSpecification())
        .body(pet)
        .when()
        .post(CREATE_PET)
        .then();
  }

  public ValidatableResponse getPet(long petId) {
    return given(new GetPetSpec().getRequestSpecification())
        .pathParam("petId",petId)
        .when()
        .get(GET_PET)
        .then();
  }

  public ValidatableResponse deletePet(long petId) {
    return given(new DeletePetSpec().getRequestSpecification())
        .pathParam("petId",petId)
        .when()
        .get(DELETE_PET)
        .then();
  }
}
