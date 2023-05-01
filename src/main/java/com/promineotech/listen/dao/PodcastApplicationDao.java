package com.promineotech.listen.dao;

import java.util.List;
import com.promineotech.listen.entity.Category;
import com.promineotech.listen.entity.Podcast;


public interface PodcastApplicationDao {

  
  List<Podcast> fetchPodcasts(Category category);

}
