package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.data.StatusData;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class PetDTO {

  private long id;
  private CategoryDTO category;
  private String name;
  private List<String> photoUrls;
  private List<TagDTO> tags;
  private StatusData status;



}
