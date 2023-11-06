package org.example.specs.pet;

import static io.restassured.RestAssured.given;

import org.example.specs.AbsSpec;


public class GetPetSpec extends AbsSpec {

  public GetPetSpec() {
    super(given());
  }
}
