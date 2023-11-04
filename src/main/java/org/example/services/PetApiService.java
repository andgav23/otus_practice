package org.example.services;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.dto.PetDTO;

public class PetApiService {
  private final RequestSpecification requestSpecification;
  private final static String BASE_URL = "https://petstore.swagger.io/v2";
  private final static String METHOD_NAME = "/pet";

  public PetApiService() {
    requestSpecification = given()
        .relaxedHTTPSValidation()
        .baseUri(BASE_URL)
        .contentType(ContentType.JSON)
        .log().all();
  }

  public ValidatableResponse createPet(PetDTO pet) {
   return given(requestSpecification)
        .body(pet)
        .when()
        .post(METHOD_NAME)
        .then()
        .log().all();

  }
}
