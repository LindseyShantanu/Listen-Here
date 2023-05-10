package com.promineotech.listen.entity;

//import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Favorites {
  private int favorites_id;
  private int podcast_fk; //Originally had this as a Map<Integer, Podcast>
}
