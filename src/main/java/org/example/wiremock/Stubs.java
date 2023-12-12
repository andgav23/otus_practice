package org.example.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.example.utils.JsonBuilder;

public class Stubs {
  private JsonBuilder jsonBuilder;
  public WireMockServer wireMockServer;

  public Stubs wireMockStart() {
    wireMockServer = new WireMockServer(options().stubCorsEnabled(true));
    wireMockServer.start();
    jsonBuilder = new JsonBuilder();
    return this;
  }

  public Stubs wireMockStop() {
    wireMockServer.resetAll();
    return this;
  }

  public Stubs getAllUsers(String responseFileName) {
    //jsonBuilder.setJsonObject(responseFileName);
    wireMockServer.stubFor(get("/users/get/all")

        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBodyFile("json/" + responseFileName)));
    return this;
  }

  public Stubs getUserOne(String responseFileName) {
    //jsonBuilder.setJsonObject(responseFileName);
    wireMockServer.stubFor(get("/users/get/user-id1")
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBodyFile("json/" + responseFileName)));
    return this;
  }
  public Stubs getUserTwo(String responseFileName) {
    //jsonBuilder.setJsonObject(responseFileName);
    wireMockServer.stubFor(get("/users/get/user-id2")
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBodyFile("json/" + responseFileName)));
    return this;
  }

  public Stubs pingPong() {
    //jsonBuilder.setJsonObject(responseFileName);
    wireMockServer.stubFor(get("/ping-pong")
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")));
    return this;
  }
}

