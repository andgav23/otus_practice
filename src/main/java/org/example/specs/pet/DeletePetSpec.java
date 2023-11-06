package org.example.specs.pet;

import static io.restassured.RestAssured.given;

import org.example.specs.AbsSpec;


public class DeletePetSpec extends AbsSpec {

  public DeletePetSpec() {
    super(given());
  }
}
