package com.promineotech.listen.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineotech.listen.entity.Category;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultCategoryDao implements CategoryDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<Category> fetchCategoriesByPodcastName(String podcastName) {
    log.debug("podcastName ={}", podcastName);
    
  }

  @Override
  public Optional<Category> addNewCategory(String category_name) {
    log.debug("category_name ={}", category_name);
    
  }

  @Override
  public Optional<Category> updateCategory(String category_name, String new_category_name) {
    log.debug("category_name ={}, new_category_name ={}", category_name, new_category_name);
  }

  @Override
  public Optional<Category> deleteCategory(String category_name) {
    log.debug("category_name ={}", category_name);
    
  }

}
