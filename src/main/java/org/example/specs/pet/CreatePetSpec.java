package org.example.specs.pet;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import org.example.specs.AbsSpec;


public class CreatePetSpec extends AbsSpec {

  public CreatePetSpec() {
    super(given()
        .contentType(ContentType.JSON));
  }
}
