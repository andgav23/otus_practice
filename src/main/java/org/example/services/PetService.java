package org.example.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.example.client.PetClient;

@Slf4j
@RequiredArgsConstructor
public class PetService {
  private final PetClient petClient;

  public boolean deletePetIfExist(long petId) {

    if (petClient.getPet(petId).extract().statusCode() == HttpStatus.SC_OK) {
      if (petClient.deletePet(petId).extract().statusCode() == HttpStatus.SC_OK) {
        log.info("Clearing resources: Pet with id {} has been deleted", petId);
        return true;
      }

    }
    log.info("Clearing resources: Pet with id {} has not been deleted", petId);
    return false;
  }
}
