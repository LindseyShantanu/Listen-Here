package com.promineotech.listen.dao;

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
import com.promineotech.jeep.dao.DefaultJeepOrderDao.CustomerResultSetExtractor;
import com.promineotech.jeep.entity.Customer;
import com.promineotech.listen.entity.Favorites;
import com.promineotech.listen.entity.Listener;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultListenerDao implements ListenerDao{

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<Listener> fetchListenerByName(String listener_name) {
    log.debug("DAO: listener_name={}", listener_name);
    
    String sql = "SELECT * FROM listener WHERE listener_name =: listener_name";
    
    Map<String, Object> params = new HashMap<>();
    params.put("listener_name", listener_name);
    
    return jdbcTemplate.query(sql, params, new RowMapper<>() {
      @Override
      public Listener mapRow(ResultSet rs, int rowNum) throws SQLException {
          // @formatter:off
          return Listener.builder()
              .listener_id(rs.getInt("listener_id"))
              .listener_name(new String(rs.getString("listener_name")))
              .favorites_fk(rs.getInt("favorites_fk"))
              .build();
          // @formatter:on
      }
    });
  }

  @Override
  public Optional<Listener> fetchListenerByFavoritesId(int favorite_id) {
    log.debug("DAO: favorite_id={}", favorite_id);
    
    String sql = "SELECT * FROM listener WHERE favorites_fk =: favorite_id";
    
    Map<String, Object> params = new HashMap<>();
    params.put("favorites_fk", favorite_id);
    
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
          .favorites_fk(rs.getInt("favorites_fk"))
          .build();
      // @formatter:on
    }
  }

  @Override
  public List<Favorites> fetchFavoritesByFavoritesId(int favoritesId) {
    log.debug("DAO: favoritesId={}", favoritesId);
    
    String sql = "SELECT * FROM favorites WHERE favorites_Id =: favoritesId";
    
    Map<String, Object> params = new HashMap<>();
    params.put("favorites_Id", favoritesId);
    
    return jdbcTemplate.query(sql, params, new RowMapper<>() {
      @Override
      public Favorites mapRow(ResultSet rs, int rowNum) throws SQLException {
          // @formatter:off
          return Favorites.builder()
              .favorites_id(rs.getInt("favoritesId"))
              .podcast_fk(rs.get)
              .build();
          // @formatter:on
      }
    });
  }

}
