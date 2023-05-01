package com.promineotech.listen.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
  private int category_id;
  private String category_name;
}
