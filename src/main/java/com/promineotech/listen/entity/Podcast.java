package com.promineotech.listen.entity;

import java.math.BigDecimal;
//import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Podcast {
  private int podcast_id;
  private String podcast_name;
  private String podcast_author;
  private BigDecimal rating;
  private int listeners;
  private String date_created;

  @JsonIgnore
  public int getpodcast_id() {
    return podcast_id;
  }
}