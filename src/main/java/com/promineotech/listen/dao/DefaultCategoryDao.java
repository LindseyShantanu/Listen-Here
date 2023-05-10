package com.promineotech.listen.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineotech.listen.entity.Category;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Component
@Slf4j
public class DefaultCategoryDao implements CategoryDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<Category> fetchCategoriesByPodcastName(String podcast_name) {
    log.debug("DAO: podcast_name ={}", podcast_name);
    
 // @formatter:off
    String sql = ""
        + "SELECT podcast.podcast_name, category.category_name "
        + "FROM podcast "
        + "INNER JOIN podcast_category ON podcast_category.podcast_fk = podcast.podcast_id "
        + "INNER JOIN category ON category.category_id = podcast_category.category_fk "
        + "WHERE podcast.podcast_name = :podcast_name";
    
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("podcast_name", podcast_name);
    
    return jdbcTemplate.query(sql, params, new RowMapper<>() {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            // @formatter:off
            return Category.builder()
                    .category_id(rs.getInt("category_id"))
                    .category_name(new String(rs.getString("category_name")))
                    .build();
            // formatter:on
        }
    });
  }

  @Override
  public Optional<Category> addNewCategory(String category_name) {
    log.debug("DAO: category_name ={}", category_name);
    
 // @formatter:off
    String sql = ""
        + "INSERT INTO category ("
        + "category_name"
        + ") VALUES ("
        + ":category_name)";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("category_name", category_name);
    
    jdbcTemplate.update(sql, params);
    return Optional.ofNullable(Category.builder()
        // @formatter:off
        .category_name(category_name)
        .build());
        // @formatter:on
    
  }

  @Override
  public Optional<Category> updateCategory(String category_name, String new_category_name) {
    log.debug("DAO: category_name ={}, new_category_name ={}", category_name, new_category_name);
    
 // @formatter:off
    String sql = ""
            + "UPDATE category "
            + "SET category_name = :new_category_name "
            + "WHERE category_name = :category_name";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("new_category_name", new_category_name);
    params.put("category_name", category_name);
    
    jdbcTemplate.update(sql, params);
    return Optional.ofNullable(Category.builder()
        // @formatter:off
        .category_name(new_category_name)
        .build());
        // @formatter:on
  }

  /*
   *Delete Category
   *given
   *param: category_name
   */
  @Override
  public Optional<Category> deleteCategory(String category_name) {
    log.debug("category_name ={}", category_name);
  //@ formatter:off
    String sql = ""
        + "DELETE FROM category "
        + "WHERE category_name = :category_name";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("category_name", category_name);


    jdbcTemplate.update(sql, params);
    return Optional.ofNullable(Category
        // @formatter:off
        .builder()
        .category_name(category_name)
        .build());
        // @formatter:on
    
  }

}
