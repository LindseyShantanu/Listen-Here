package com.promineotech.listen.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.promineotech.listen.dao.CategoryDao;
import com.promineotech.listen.entity.Category;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultCategoryService implements CategoryService {

  @Autowired
  private CategoryDao categoryDao;
 
//GET/READ operation
  @Override
  public List<Category> fetchCategoriesByPodcastName(String podcastName) {
    log.info("podcastName ={}", podcastName);
    return categoryDao.fetchCategoriesByPodcastName(podcastName);
  }

// POST/CREATE operation
  @Override
  public Optional<Category> addNewCategory(String category_name) {
    log.info("category_name ={}", category_name);
    return categoryDao.addNewCategory(category_name);
  }

// PUT/UPDATE operation
  @Override
  public Optional<Category> updateCategory(String category_name, String new_category_name) {
    log.info("category_name ={}, new_category_name ={}", category_name, new_category_name);
    return categoryDao.updateCategory(category_name, new_category_name);
  }
  
// DELETE operation
  @Override
  public Optional<Category> deleteCategory(String category_name) {
    log.info("category_name ={}", category_name);
    return categoryDao.deleteCategory(category_name);
  }

}
