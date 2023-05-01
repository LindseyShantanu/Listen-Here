package com.promineotech.listen.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineotech.listen.entity.Category;
import com.promineotech.listen.entity.Podcast;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultPodcastApplicationDao implements PodcastApplicationDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<Podcast> fetchPodcasts(Category category) {
    log.debug("DAO: category={", category);
    
 // @formatter: off
    String sql = "" 
        + "SELECT * " 
        + "FROM podcast "
        + "WHERE category_id = :category_id";
    // @formatter: on
    
    Map<String, Object> params = new HashMap<>();
    params.put("category_id", category.toString());

    return jdbcTemplate.query(sql, params, new RowMapper<>() {
      @Override
      public Podcast mapRow(ResultSet rs, int rowNum) throws SQLException {
        // @formatter: off
        return Podcast.builder()
            .podcast_id(rs.getInt("podcast_id"))
            .rating(new BigDecimal(rs.getString("rating")))
            .podcast_name(rs.getString("podcast_name"))
            .podcast_author(rs.getString("podcast_author"))
            .listeners(rs.getInt("listeners"))
            .date_created(rs.getDate("date_created"))
            .build();
        // @formatter: on
      }
    });
  }
}

 