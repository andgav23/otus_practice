package org.example.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import io.restassured.mapper.ObjectMapper;
import org.apache.http.HttpStatus;
import org.example.data.StatusData;
import org.example.dto.CategoryDTO;
import org.example.dto.PetDTO;
import org.example.dto.TagDTO;
import org.junit.jupiter.api.Test;
import java.util.List;


class PetApiTest {
  private final long PET_ID = 5;
  private final String PET_NAME = "Andrey";
  private final List<String> PET_PHOTO_URLS = List.of("url1", "url2");
  private final long CATEGORY_ID = 18;
  private final String CATEGORY_NAME = "Bug";
  private final List<TagDTO> PET_TAGS = List.of(TagDTO.builder()
      .id(59)
      .name("tag_1")
      .build(), TagDTO.builder()
      .id(60)
      .name("tag_2")
      .build());

  @Test
  public void checkCreatePet() {

    PetApiService petApiService = new PetApiService();
    PetDTO pet = PetDTO.builder()
        .id(PET_ID)
        .category(CategoryDTO.builder()
            .id(CATEGORY_ID)
            .name(CATEGORY_NAME).build()
        )
        .name(PET_NAME)
        .photoUrls(PET_PHOTO_URLS)
        .tags(PET_TAGS)
        .status(StatusData.PENDING).build();
    PetDTO petResponse = petApiService.createPet(pet).statusCode(HttpStatus.SC_OK).extract().body().as(PetDTO.class);
    assertEquals(pet, petResponse);


  }

}