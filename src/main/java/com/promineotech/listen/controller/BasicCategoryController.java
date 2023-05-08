package com.promineotech.listen.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.listen.entity.Category;
import com.promineotech.listen.service.CategoryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicCategoryController implements CategoryController {

  @Autowired
  private CategoryService categoryService;
  
  @Override
  public List<Category> fetchCategoriesByPodcastName(String podcastName) {
    log.info("podcastName ={}", podcastName);
    return categoryService.fetchCategoriesByPodcastName(podcastName);
  }

  @Override
  public Optional<Category> newCategory(String category_name) {
    log.info("category_name ={}", category_name);
    return categoryService.addNewCategory(category_name);
  }

  @Override
  public Optional<Category> updateCategory(String category_name, String new_category_name) {
    log.info("category_name ={}, new_category_name ={}", category_name, new_category_name);
    return categoryService.updateCategory(category_name, new_category_name);
  }

  @Override
  public Optional<Category> deleteCategory(String category_name) {
    log.info("category_name ={}", category_name);
    return categoryService.deleteCategory(category_name);
  }


}
