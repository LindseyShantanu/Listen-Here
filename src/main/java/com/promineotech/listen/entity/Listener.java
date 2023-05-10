package com.promineotech.listen.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Listener {
  private int listener_id;
  private String listener_name;
  private int favorite_id;
  private String about;
}
