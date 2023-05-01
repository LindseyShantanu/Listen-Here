package com.promineotech.listen.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.listen.entity.Category;
import com.promineotech.listen.entity.Podcast;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/podcasts") //tells spring to map pods uri to the class BasicAP
@OpenAPIDefinition(info = @Info(title = "Pocast Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface ApplicationController {

  @Operation(
      summary = "View available podcasts",
      description = "Returns the available podcasts",
      responses = {
          @ApiResponse(
              responseCode = "200", 
              description = "The available podcasts are returned", 
              content = @Content(mediaType = "application/json", 
                  schema = @Schema(implementation = Podcast.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No podcasts were found with the input criteria",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "category", 
              allowEmptyValue = false, 
              required = false, 
              description = "The category name(i.e. 'True Crime')")
      }
  )
  
  @GetMapping()
  @ResponseStatus(code = HttpStatus.OK)
  List<Podcast> fetchPodcasts(
      @RequestParam(required = false) 
        Category category);
  
}
