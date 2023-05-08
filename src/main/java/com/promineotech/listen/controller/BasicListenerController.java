package com.promineotech.listen.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.listen.entity.Favorites;
import com.promineotech.listen.entity.Listener;
import com.promineotech.listen.service.ListenerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicListenerController implements ListenerController {

  @Autowired
  private ListenerService listenerService;

  @Override
  public List<Listener> fetchListenerByName(String listener_name) {
    log.info("listener_name={}", listener_name);
    return listenerService.fetchListenerByName(listener_name);
  }

  @Override
  public Optional<Listener> fetchListenerByFavoritesId(int favorite_id) {
    log.info("favorite_id={}", favorite_id);
    return listenerService.fetchListenerByFavoritesId(favorite_id);
  }
  
  @Override
  public List<Favorites> fetchFavoritesByFavoritesId(int favoritesId) {
    log.info("favoritesId={}", favoritesId);
    return listenerService.fetchFavoritesByFavoritesId(favoritesId);
  }


}
