package com.promineotech.listen.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.listen.entity.Category;
import com.promineotech.listen.entity.Podcast;
import com.promineotech.listen.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicApplicationController implements ApplicationController {

  @Autowired
  private ApplicationService applicationService;
  
  @Override
  public List<Podcast> fetchPodcasts(Category category) {
    log.debug("category={}", category); //causes logger to log whatever is passed in
    return applicationService.fetchPodcasts(category);
  }

}
