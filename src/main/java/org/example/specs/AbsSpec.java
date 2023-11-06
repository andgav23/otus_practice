package org.example.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;



public abstract class AbsSpec {
  private RequestSpecBuilder builder = new RequestSpecBuilder();
  protected RequestSpecification requestSpecification;
  private final String baseUrl = System.getProperty("base.url");

  public AbsSpec(RequestSpecification requestSpecification) {
    this.builder
        .addRequestSpecification(requestSpecification)
        .setBaseUri(baseUrl)
        .setRelaxedHTTPSValidation();
    this.requestSpecification = builder.build();
  }

  public RequestSpecification getRequestSpecification(){
    return this.requestSpecification;
  }

}
