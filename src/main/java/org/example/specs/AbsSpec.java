package org.example.specs;

import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;


public abstract class AbsSpec {
  private RequestSpecBuilder builder = new RequestSpecBuilder();
  protected RequestSpecification requestSpecification;
  private final String BASE_URL = System.getProperty("base.url");

  public AbsSpec(RequestSpecification requestSpecification) {
    this.builder
        .addRequestSpecification(requestSpecification)
        .setBaseUri(BASE_URL)
        .setRelaxedHTTPSValidation();
    this.requestSpecification = builder.build();
  }

  public RequestSpecification getRequestSpecification(){
    return this.requestSpecification;
  }

}
