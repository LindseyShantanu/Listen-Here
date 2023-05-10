package com.promineotech.listen.dao;

import java.util.List;
import java.util.Optional;
import com.promineotech.listen.entity.Category;

public interface CategoryDao {

  List<Category> fetchCategoriesByPodcastName(String podcast_name);

  Optional<Category> addNewCategory(String category_name);

  Optional<Category> updateCategory(String category_name, String new_category_name);

  Optional<Category> deleteCategory(String category_name);


}
