package com.promineotech.listen.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.listen.dao.PodcastApplicationDao;
import com.promineotech.listen.entity.Podcast;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultPodcastService implements PodcastService{

  @Autowired
  private PodcastApplicationDao podcastApplicationDao;
  
//GET/READ operation
  @Transactional
  @Override
  public List<Podcast> fetchPodcastsByCategory(String category) {
    log.info("The fetchPodcastsByCategory method was called with category={}", 
        category);
   
    List<Podcast> podcasts = podcastApplicationDao.fetchPodcasts(category);
    
    if(podcasts.isEmpty()) {
      String msg = String.format("No podcasts found with category=%s",
          category);
      throw new NoSuchElementException(msg);
    }
    
    return podcasts;
  }

//POST/CREATE operation
  @Override
  public Optional<Podcast> newPodcast(String podcast_name, String podcast_author, 
      BigDecimal rating, int listeners, Date date_created) {
    log.info("The newPodcast method was called with "
        + "podcast_name={},  podcast_author={},"
        + "  rating={}, listeners={}, date_created={}", 
        podcast_name, podcast_author, rating, listeners, date_created);
    return podcastApplicationDao.newPodcast(podcast_name, podcast_author, rating, listeners, date_created);
  }

//PUT/UPDATE operation
  @Override
  public Optional<Podcast> updatePodcast(String podcast_name, String podcast_author,
      BigDecimal rating, int listeners, Date date_created, 
      String new_podcast_name, String new_podcast_author, 
      BigDecimal new_rating, int new_listeners, Date new_date_created) {
    log.info("podcast_name={}, podcast_author={}, rating={}, listeners={}, date_created={}"
        + "new_podcast_name={}, new_podcast_author={}, new_rating={}, new_listeners={}, new_date_created={}",
        podcast_name, podcast_author, rating, listeners, date_created,
        new_podcast_name, new_podcast_author, new_rating, new_listeners, new_date_created);
    return podcastApplicationDao.updatePodcast(podcast_name, podcast_author, rating, listeners, date_created,
        new_podcast_name, new_podcast_author, new_rating, new_listeners, new_date_created);
  }

//DELETE operation
  @Override
  public Optional<Podcast> deletePodcast(String podcast_name, String podcast_author) {
    log.info("The updatePodcast method was called with "
        + "podcast_name={},  podcast_author={},", 
        podcast_name, podcast_author);
    return podcastApplicationDao.deletePodcast(podcast_name, podcast_author);
  }

}
