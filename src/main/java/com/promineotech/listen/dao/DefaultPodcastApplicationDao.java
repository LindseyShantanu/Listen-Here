package com.promineotech.listen.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
//import com.promineotech.listen.entity.Category;
import com.promineotech.listen.entity.Podcast;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultPodcastApplicationDao implements PodcastApplicationDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  // GET/READ operation
  @Override
  public List<Podcast> fetchPodcasts(String category) {
    log.debug("DAO: category={}", category);

    // @formatter: off
    String sql = "SELECT podcast.*, category.* "
        + "FROM podcast "
        + "INNER JOIN podcast_category ON podcast_category.podcast_id = podcast.podcast_id "
        + "INNER JOIN category ON category.category_id = podcast_category.category_id "
        + "WHERE category_name = :category_name";
    // @formatter: on

    Map<String, Object> params = new HashMap<>();
    params.put("category_name", category);

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
            .date_created(rs.getString("date_created"))
            .build();
        // @formatter: on
      }
    });
  }

  // POST/CREATE operation
  @Override
  public Optional<Podcast> newPodcast(String podcast_name, String podcast_author, BigDecimal rating,
      int listeners, String date_created) {
    log.debug("DAO: podcast_name={},  podcast_author={},rating={}, listeners={}, date_created={}",
        podcast_name, podcast_author, rating, listeners, date_created);
    
    // @formatter:off
    String sql = ""
        + "INSERT INTO podcast ("
        + "podcast_name, podcast_author, rating, listeners, date_created"
        + ") VALUES ("
        + ":podcast_name, :podcast_author, :rating, :listeners, :date_created)";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("podcast_name", podcast_name);
    params.put("podcast_author", podcast_author);
    params.put("rating", rating);
    params.put("listeners", listeners);
    params.put("date_created", date_created);

    jdbcTemplate.update(sql, params);
    return Optional.ofNullable(Podcast.builder()
        // @formatter:off
        .podcast_name(podcast_name)
        .podcast_author(podcast_author)
        .rating(rating)
        .listeners(listeners)
        .date_created(date_created)
        .build());
        // @formatter:on
  }

  // PUT/UPDATE operation
  @Override
  public Optional<Podcast> updatePodcast(String podcast_name, String podcast_author,
      String new_podcast_name, String new_podcast_author, BigDecimal new_rating, 
      int new_listeners, String new_date_created) {
    log.debug("DAO: podcast_name={}, podcast_author={}, "
        + "new_podcast_name={}, new_podcast_author={}, new_rating={}, new_listeners={}, new_date_created={}",
        podcast_name, podcast_author,
        new_podcast_name, new_podcast_author, new_rating, new_listeners, new_date_created);

    // @formatter:off
    String sql = ""
        + "UPDATE podcast "
        + "SET "
        + "podcast_name = :new_podcast_name, "
        + "podcast_author = :new_podcast_author, "
        + "rating = :new_rating, "
        + "listeners = :new_listeners, "
        + "date_created = :new_date_created "
        + "WHERE (podcast_name = :podcast_name AND "
        + "podcast_author = :podcast_author)";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("new_podcast_name", new_podcast_name);
    params.put("new_podcast_author", new_podcast_author);
    params.put("new_rating", new_rating);
    params.put("new_listeners", new_listeners);
    params.put("new_date_created", new_date_created);
    params.put("podcast_name", podcast_name);
    params.put("podcast_author", podcast_author);
   
    jdbcTemplate.update(sql, params);
    return Optional.ofNullable(Podcast.builder()
        // @formatter:off
        .podcast_name(new_podcast_name)
        .podcast_author(new_podcast_author)
        .rating(new_rating)
        .listeners(new_listeners)
        .date_created(new_date_created)
        .build());
        // @formatter:on
  }

  // DELETE operation
  @Override
  public Optional<Podcast> deletePodcast(String podcast_name, String podcast_author) {
    // @ formatter:off
    String sql = "DELETE FROM podcast " 
        + "WHERE podcast_name = :podcast_name AND "
        + "podcast_author = :podcast_author";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("podcast_name", podcast_name);
    params.put("podcast_author", podcast_author);

    jdbcTemplate.update(sql, params);

    // @formatter:off
    return Optional.ofNullable(Podcast.builder()
        .podcast_name(podcast_name)
        .podcast_author(podcast_author)
        .build());
        // @formatter:on
  }

}

