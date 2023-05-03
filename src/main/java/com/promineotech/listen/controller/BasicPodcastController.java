package com.promineotech.listen.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.listen.entity.Podcast;
import com.promineotech.listen.service.PodcastService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicPodcastController implements PodcastController {

  @Autowired
  private PodcastService podcastService;

  /*
   * GET/Read operation, retrieve podcast information based on user input of category 
   */
  @Override
  public List<Podcast> fetchPodcastsByCategory(String category) {
    log.info("category={}", category); //causes logger to log whatever is passed in
    return podcastService.fetchPodcastsByCategory(category);
  }

  /*
   * POST/Create operation, add a new podcast to the db
   * All fields Not Null except date_created 
   */
  @Override
  public Optional<Podcast> newPodcast(String podcast_name, String podcast_author, BigDecimal rating,
      int listeners, Date date_created) {
    log.info("podcast_name={},  podcast_author={},  rating={}, listeners={}, date_created={}",
        podcast_name, podcast_author, rating, listeners, date_created);
    return podcastService.newPodcast( podcast_name, podcast_author, rating, listeners, date_created);
  }

  /*
   * PUT/Update operation, change existing podcast's info in the db
   */
  @Override
  public Optional<Podcast> updatePodcast(String podcast_name, String podcast_author,
      BigDecimal rating, int listeners, Date date_created, String new_podcast_name, String new_podcast_author,
      BigDecimal new_rating, int new_listeners, Date new_date_created) {
    log.info("podcast_name={}, podcast_author={}, rating={}, listeners={}, date_created={}"
        + "new_podcast_name={}, new_podcast_author={}, new_rating={}, new_listeners={}, new_date_created={}",
        podcast_name, podcast_author, rating, listeners, date_created,
        new_podcast_name, new_podcast_author, new_rating, new_listeners, new_date_created);
    return podcastService.updatePodcast(podcast_name, podcast_author, rating, listeners, date_created,
        new_podcast_name, new_podcast_author, new_rating, new_listeners, new_date_created);
  }

  /*
   * DELETE operation, delete a podcast from the db
   */
  @Override
  public Optional<Podcast> deletePodcast(String podcast_name, String podcast_author) {
    log.info("podcast_name={},  podcast_author={}", podcast_name, podcast_author);
    return podcastService.deletePodcast(podcast_name, podcast_author);
  }

}
