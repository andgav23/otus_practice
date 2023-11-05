package org.example.services;

import groovy.util.logging.Slf4j;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import org.example.client.PetClient;
@Slf4j
@RequiredArgsConstructor
public class ResourceCleaner {
  private final PetClient petClient;

  public boolean deletePetIfExist(long petId) {

    if (petClient.getPet(petId).extract().statusCode() == 200) {
      return petClient.deletePet(petId).extract().statusCode() == 200;
    }
    return false;
  }
}
