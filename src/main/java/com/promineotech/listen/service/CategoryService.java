package com.promineotech.listen.service;

import java.util.List;
import java.util.Optional;
import com.promineotech.listen.entity.Category;

public interface CategoryService {

  List<Category> fetchCategoriesByPodcastName(String podcastName);

  Optional<Category> addNewCategory(String category_name);

  Optional<Category> updateCategory(String category_name, String new_category_name);

  Optional<Category> deleteCategory(String category_name);

}
