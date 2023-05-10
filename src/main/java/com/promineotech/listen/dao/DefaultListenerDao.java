package com.promineotech.listen.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
//import com.promineotech.listen.entity.Favorites;
import com.promineotech.listen.entity.Listener;
import com.promineotech.listen.entity.Podcast;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultListenerDao implements ListenerDao{

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<Listener> fetchListenerByName(String listener_name) {
    log.debug("DAO: listener_name={}", listener_name);
    
    String sql = "SELECT * FROM listener WHERE listener_name = :listener_name";
    
    Map<String, Object> params = new HashMap<>();
    params.put("listener_name", listener_name);
    
    return jdbcTemplate.query(sql, params, new RowMapper<>() {
      @Override
      public Listener mapRow(ResultSet rs, int rowNum) throws SQLException {
          // @formatter:off
          return Listener.builder()
              .listener_id(rs.getInt("listener_id"))
              .listener_name(new String(rs.getString("listener_name")))
              .favorite_id(rs.getInt("favorite_id"))
              .about(new String(rs.getString("about")))
              .build();
          // @formatter:on
      }
    });
  }

  @Override
  public Optional<Listener> fetchListenerByFavoritesId(int favorite_id) {
    log.debug("DAO: favorite_id={}", favorite_id);
    
    String sql = "SELECT * FROM listener WHERE favorite_id = :favorite_id";
    
    Map<String, Object> params = new HashMap<>();
    params.put("favorite_id", favorite_id);
    
    return Optional.ofNullable(
        jdbcTemplate.query(sql, params, new ListenerResultSetExtractor()));
  }

  class ListenerResultSetExtractor implements ResultSetExtractor<Listener> {
    @Override
    public Listener extractData(ResultSet rs) 
        throws SQLException, DataAccessException {
      rs.next();
      
      // @formatter:off
      return Listener.builder()
          .listener_id(rs.getInt("listener_id"))
          .listener_name(rs.getString("listener_name"))
          .favorite_id(rs.getInt("favorite_id"))
          .about(rs.getString("about"))
          .build();
      // @formatter:on
    }
  }

  /*
   * Currently works with 1 favorites id connected to 1 podcast fk/id
   * Would like listeners to be able to have more than 1 favorite podcast
   * while being recognized by their sole favorite id.
   */
  @Override
  public List<Podcast> fetchFavoritesByFavoritesId(int favorites_id) {
    log.debug("DAO: favorites_id={}", favorites_id);
    
    String sql = "SELECT * "
        + "FROM podcast "
        + "JOIN favorites on favorites.podcast_fk = podcast.podcast_id "
        + "WHERE favorites_id = :favorites_id";
    
    Map<String, Object> params = new HashMap<>();
    params.put("favorites_id", favorites_id);
    
    return jdbcTemplate.query(sql, params, new RowMapper<>() {
      @Override
      public Podcast mapRow(ResultSet rs, int rowNum) throws SQLException {
          // @formatter:off
          return Podcast.builder()
              .podcast_id(rs.getInt("podcast_id"))
              .rating(new BigDecimal(rs.getString("rating")))
              .podcast_name(rs.getString("podcast_name"))
              .podcast_author(rs.getString("podcast_author"))
              .listeners(rs.getInt("listeners"))
              .date_created(rs.getString("date_created"))
              .build();
              /*
               * entity podcast_fk was a map object
               * Map<Integer, Podcast> podcast_fk;
               * changed to int
               */
          // @formatter:on
      }
    });
  }

}
