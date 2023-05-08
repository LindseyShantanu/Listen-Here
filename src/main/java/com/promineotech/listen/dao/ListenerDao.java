package com.promineotech.listen.dao;

import java.util.List;
import java.util.Optional;
import com.promineotech.listen.entity.Favorites;
import com.promineotech.listen.entity.Listener;

public interface ListenerDao {

  List<Listener> fetchListenerByName(String listener_name);

  Optional<Listener> fetchListenerByFavoritesId(int favorite_id);

  List<Favorites> fetchFavoritesByFavoritesId(int favoritesId);

}
