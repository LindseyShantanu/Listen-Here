package com.promineotech.listen.controller;

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
import com.promineotech.listen.entity.Category;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/category") 
@OpenAPIDefinition(info = @Info(title = "PocastApp"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface CategoryController {
//GET retrieves podcast information
//fetchPodcastsByCategory()
  @Operation(
      summary = "View available categories",
      description = "Returns a list of categories given a podcast name",
      responses = {
          @ApiResponse(
              responseCode = "200", 
              description = "The associated categories are returned", 
              content = @Content(mediaType = "application/json", 
                  schema = @Schema(implementation = Category.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No categories were found with the input criteria",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "podcast_name", 
              allowEmptyValue = false, 
              required = true, 
              description = "The podcast name (i.e. 'Serial')")
      }
  )
  
  @GetMapping(value = "/category-by-podcast-name")
  @ResponseStatus(code = HttpStatus.OK)
  List<Category> fetchCategoriesByPodcastName(
      @RequestParam(required = true) 
        String podcast_name);

//POST creates a new podcast in database
//newPodcast()  
  //@formatter:off
  @Operation(
      summary = "Adds a category",
      description = "Creates a new category with required fields: category name",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "New category created",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Category.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid request parameters",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Unable to create new category with the information given",
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
              name = "category_name",
              allowEmptyValue = false,
              required = true,
              description = "Name of category"),
      }
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Optional<Category> newCategory(
      @RequestParam(required = true)
      String category_name);
  // @formatter:on
  
//PUT updates a podcast's information
//updatePodcast()
//@formatter:off
  @Operation(
      summary = "Updates a category",
      description = "Updates a category's name",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Category information was updated successfully.",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Category.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid request parameters",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Unable to update category with the information given",
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
                  name = "category_name",
                  allowEmptyValue = false,
                  required = true,
                  description = "Name of existing category"),
              @Parameter(
                  name = "new_category_name",
                  allowEmptyValue = false,
                  required = true,
                  description = "Name of updated category")
          }
  )

  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  Optional<Category> updateCategory(
      @RequestParam(required = true)
      String category_name,
      @RequestParam(required = true)
      String new_category_name);
  // @formatter:on
  
//DELETE-deletes a cast member from the database
//deletePodcast()
    // @formatter:off   
    @Operation(
        summary = "Deletes a Category",
        description = "Deletes a Category from the Category list",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Category information was deleted successfully.",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Category.class))),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid request parameters",
                content = @Content(
                    mediaType = "application/json")),
            @ApiResponse(
                responseCode = "404",
                description = "Unable to delete Category with the information given",
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
                    name = "category_name",
                    allowEmptyValue = false,
                    required = true,
                    description = "Name of podcast")
            }
        )
    
    @DeleteMapping
    @ResponseStatus(code = HttpStatus.OK)
    Optional<Category> deleteCategory(
        @RequestParam(required = true)
        String category_name);
}