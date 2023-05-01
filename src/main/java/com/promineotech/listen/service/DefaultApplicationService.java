package com.promineotech.listen.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.listen.dao.PodcastApplicationDao;
import com.promineotech.listen.entity.Category;
import com.promineotech.listen.entity.Podcast;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultApplicationService implements ApplicationService{

  @Autowired
  private PodcastApplicationDao podcastApplicationDao;
  
  @Transactional(readOnly = true)
  @Override
  public List<Podcast> fetchPodcasts(Category category) {
    log.info("The fetchPodcasts method was called with category={}", 
        category);
   
    List<Podcast> podcasts = podcastApplicationDao.fetchPodcasts(category);
    
    if(podcasts.isEmpty()) {
      String msg = String.format("No podcasts found with category=%s",
          category);
      throw new NoSuchElementException(msg);
    }
    
    return podcasts;
  }

}
