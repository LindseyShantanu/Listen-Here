package com.promineotech.listen.controller;

import java.math.BigDecimal;
//import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
//import com.promineotech.listen.entity.Category;
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
@RequestMapping("/podcast") 
@OpenAPIDefinition(info = @Info(title = "PocastApp"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface PodcastController {

//GET retrieves podcast information
//fetchPodcastsByCategory()
  @Operation(
      summary = "View available podcasts",
      description = "Returns a list of podcasts given a category name",
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
              required = true, 
              description = "The category name(i.e. 'True Crime')")
      }
  )
  
  @GetMapping(value = "/podcast-by-category-name")
  @ResponseStatus(code = HttpStatus.OK)
  List<Podcast> fetchPodcastsByCategory(
      @RequestParam(required = true) 
        String category);

//POST creates a new podcast in database
//newPodcast()  
  //@formatter:off
  @Operation(
      summary = "Adds a podcast",
      description = "Creates a new podcast with required fields: podcast name, podcast author,"
          + " rating, and listeners",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "New podcast created",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Podcast.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid request parameters",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Unable to create new podcast with the information given",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error has occurred.",
              content = @Content(
                  mediaType = "application/json"))
      },
      
      parameters = {
          @Parameter(
              name = "podcast_name",
              allowEmptyValue = false,
              required = true,
              description = "Name of podcast"),
          @Parameter(
              name = "podcast_author",
              allowEmptyValue = false,
              required = true,
              description = "Author/Speaker of podcast"),
          @Parameter(
              name = "rating",
              allowEmptyValue = false,
              required = true,
              description = "Rating on scale of 0.0 to 5.0"),
          @Parameter(
              name = "listeners",
              allowEmptyValue = false,
              required = true,
              description = "Number of listeners in this app"),
          @Parameter(
              name = "date_created",
              allowEmptyValue = true,
              required = false,
              description = "Date when the podcast first started")
      }
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Optional<Podcast> newPodcast(
      @RequestParam(required = true)
      String podcast_name,
      @RequestParam(required = true)
      String podcast_author,
      @RequestParam(required = true)
      BigDecimal rating,
      @RequestParam(required = true)
      int listeners,
      @RequestParam(required = false)
      String date_created);
  // @formatter:on
  
//PUT updates a podcast's information
//updatePodcast()
//@formatter:off
  @Operation(
      summary = "Updates a Podcast",
      description = "Updates a Podcast's information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Podcast information was updated successfully.",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Podcast.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid request parameters",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Unable to update Podcast with the information given",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error has occurred.",
              content = @Content(
                  mediaType = "application/json"))
      },
          
          parameters = {
              @Parameter(
                  name = "podcast_name",
                  allowEmptyValue = false,
                  required = true,
                  description = "Name of podcast"),
              @Parameter(
                  name = "podcast_author",
                  allowEmptyValue = false,
                  required = true,
                  description = "Author/Speaker of podcast"),
              @Parameter(
                  name = "new_podcast_name",
                  allowEmptyValue = false,
                  required = true,
                  description = "new_Name of podcast"),
              @Parameter(
                  name = "new_podcast_author",
                  allowEmptyValue = false,
                  required = true,
                  description = "new_Author/Speaker of podcast"),
              @Parameter(
                  name = "new_rating",
                  allowEmptyValue = false,
                  required = true,
                  description = "new_Rating on scale of 0.0 to 5.0"),
              @Parameter(
                  name = "new_listeners",
                  allowEmptyValue = false,
                  required = true,
                  description = "new_Number of listeners in this app"),
              @Parameter(
                  name = "new_date_created",
                  allowEmptyValue = false,
                  required = true,
                  description = "new_Date when the podcast first started")
          }
  )

  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  Optional<Podcast> updatePodcast(
      @RequestParam(required = true)
      String podcast_name,
      @RequestParam(required = true)
      String podcast_author,
      @RequestParam(required = true)
      String new_podcast_name,
      @RequestParam(required = true)
      String new_podcast_author,
      @RequestParam(required = true)
      BigDecimal new_rating,
      @RequestParam(required = true)
      int new_listeners,
      @RequestParam(required = true)
      String new_date_created);
  // @formatter:on
  
//DELETE-deletes a cast member from the database
//deletePodcast()
    // @formatter:off   
    @Operation(
        summary = "Deletes a podcast",
        description = "Deletes a podcast from the podcast list",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Podcast information was deleted successfully.",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Podcast.class))),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid request parameters",
                content = @Content(
                    mediaType = "application/json")),
            @ApiResponse(
                responseCode = "404",
                description = "Unable to delete Podcast with the information given",
                content = @Content(
                    mediaType = "application/json")),
            @ApiResponse(
                responseCode = "500",
                description = "An unplanned error has occurred.",
                content = @Content(
                    mediaType = "application/json"))
        },
        
            parameters = {
                @Parameter(
                    name = "podcast_name",
                    allowEmptyValue = false,
                    required = true,
                    description = "Name of podcast"),
                @Parameter(
                    name = "podcast_author",
                    allowEmptyValue = false,
                    required = true,
                    description = "Author/Speaker of podcast"),

            }
        )
    
    @DeleteMapping
    @ResponseStatus(code = HttpStatus.OK)
    Optional<Podcast> deletePodcast(
        @RequestParam(required = true)
        String podcast_name,
        @RequestParam(required = true)
        String podcast_author);
}
