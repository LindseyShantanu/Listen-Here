package com.promineotech.listen.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Podcast_Category {
  private int podcast_fk;
  private int category_fk;
}
