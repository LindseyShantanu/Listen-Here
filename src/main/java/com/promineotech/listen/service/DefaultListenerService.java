package com.promineotech.listen.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.listen.dao.ListenerDao;
//import com.promineotech.listen.entity.Favorites;
import com.promineotech.listen.entity.Listener;
import com.promineotech.listen.entity.Podcast;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultListenerService implements ListenerService {

  @Autowired
  private ListenerDao listenerDao;
  
  @Override
  public List<Listener> fetchListenerByName(String listener_name) {
    log.info("fetchListenerByName method has been called with for listener_name={}", listener_name);
    return listenerDao.fetchListenerByName(listener_name);
  }

  @Override
  public Optional<Listener> fetchListenerByFavoritesId(int favorite_id) {
    log.info("fetchListenerByFavoritesId method has been called with for favorite_id={}", favorite_id);
    return listenerDao.fetchListenerByFavoritesId(favorite_id);
  }

  @Override
  public List<Podcast> fetchFavoritesByFavoritesId(int favorites_id) {
    log.info("fetchFavoritesByFavoritesId method has been called with for favorites_id={}", favorites_id);
    return listenerDao.fetchFavoritesByFavoritesId(favorites_id);
  }

}
