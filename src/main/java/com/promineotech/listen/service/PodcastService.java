package com.promineotech.listen.service;

import java.math.BigDecimal;
//import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.promineotech.listen.entity.Podcast;

public interface PodcastService {
  
  List<Podcast> fetchPodcastsByCategory(String category);

  Optional<Podcast> newPodcast(String podcast_name, String podcast_author, BigDecimal rating,
      int listeners, String date_created); 

  Optional<Podcast> updatePodcast(String podcast_name, String podcast_author,
      String new_podcast_name, String new_podcast_author, 
      BigDecimal new_rating, int new_listeners, String new_date_created);

  Optional<Podcast> deletePodcast(String podcast_name, String podcast_author);

}
