package org.example.data.factory;

import org.example.data.StatusData;
import org.example.dto.CategoryDTO;
import org.example.dto.PetDTO;
import org.example.dto.TagDTO;
import java.util.List;

public class PetDataFactory {
  private final String petName = "Andrey";
  private final List<String> petPhotoUrls = List.of("url1", "url2");
  private final long categoryId = 18;
  private final String categoryName = "Bug";
  private final List<TagDTO> petTags = List.of(TagDTO.builder().id(59).name("tag_1").build(), TagDTO.builder().id(60).name("tag_2").build());
  private final long notExistingPetId = 1111111111;


  public PetDTO createPetWithFullFields() {
    PetDTO pet = PetDTO.builder()
        .category(CategoryDTO.builder().id(categoryId).name(categoryName).build())
        .name(petName)
        .photoUrls(petPhotoUrls)
        .tags(petTags)
        .status(StatusData.PENDING)
        .build();
    return pet;
  }

  public PetDTO createEmptyPet() {
    return PetDTO.builder().build();
  }

  public long getNotExistingPetId() {
    return notExistingPetId;
  }


}
