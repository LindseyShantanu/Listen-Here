package com.promineotech.listen.service;

import java.util.List;
import com.promineotech.listen.entity.Category;
import com.promineotech.listen.entity.Podcast;

public interface ApplicationService {
  
  List<Podcast> fetchPodcasts(Category category);

}
