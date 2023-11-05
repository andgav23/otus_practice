package org.example.data.factory;

import org.example.data.StatusData;
import org.example.dto.CategoryDTO;
import org.example.dto.PetDTO;
import org.example.dto.TagDTO;
import java.util.List;

public class PetDataFactory {
  private final long PET_ID = 500;
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


  public PetDTO createPetWithFullFields() {
    return PetDTO.builder()
        .id(PET_ID)
        .category(CategoryDTO.builder()
            .id(CATEGORY_ID)
            .name(CATEGORY_NAME).build()
        )
        .name(PET_NAME)
        .photoUrls(PET_PHOTO_URLS)
        .tags(PET_TAGS)
        .status(StatusData.PENDING).build();
  }

  public PetDTO createPetWithoutId() {
    return PetDTO.builder()
        .category(CategoryDTO.builder()
            .id(CATEGORY_ID)
            .name(CATEGORY_NAME).build()
        )
        .name(PET_NAME)
        .photoUrls(PET_PHOTO_URLS)
        .tags(PET_TAGS)
        .status(StatusData.PENDING).build();
  }


}
