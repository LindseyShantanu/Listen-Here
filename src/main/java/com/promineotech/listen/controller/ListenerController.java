package com.promineotech.listen.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.listen.entity.Favorites;
import com.promineotech.listen.entity.Listener;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/listener") 
@OpenAPIDefinition(info = @Info(title = "PocastApp"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface ListenerController {

  // fetchListenerByName
  @Operation(
      summary = "View listeners with given name",
      description = "Returns a list of listeners by name",
      responses = {
          @ApiResponse(
              responseCode = "200", 
              description = "The available listeners are returned", 
              content = @Content(mediaType = "application/json", 
                  schema = @Schema(implementation = Listener.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No listeners were found with the input criteria",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "listener_name", 
              allowEmptyValue = false, 
              required = true, 
              description = "The listener's name(i.e. 'Sarah Jones')")
      }
  )
  
  @GetMapping(value = "/listeners-by-listener-name")
  @ResponseStatus(code = HttpStatus.OK)
  List<Listener> fetchListenerByName(
      @RequestParam(required = true) 
        String listener_name);
  
  // fetchListenersByFavoritesId
  @Operation(
      summary = "View listeners by favorites Id",
      description = "Returns a listener by Favorites Id",
      responses = {
          @ApiResponse(
              responseCode = "200", 
              description = "The listeners with selected favorites are returned", 
              content = @Content(mediaType = "application/json", 
                  schema = @Schema(implementation = Listener.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No listeners were found with the input criteria",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "favorite_fk", 
              allowEmptyValue = false, 
              required = true, 
              description = "The id associate with the listener's favoites")
      }
  )
  
  @GetMapping(value = "/listeners-by-favoites-id")
  @ResponseStatus(code = HttpStatus.OK)
  Optional<Listener> fetchListenerByFavoritesId(
      @RequestParam(required = true) 
        int favorite_id);
  
  // fetchFavoritesByFavoitesId
  @Operation(
      summary = "View Favorites",
      description = "Returns a list of Listener's Favorites",
      responses = {
          @ApiResponse(
              responseCode = "200", 
              description = "The listener's favorites are returned", 
              content = @Content(mediaType = "application/json", 
                  schema = @Schema(implementation = Listener.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No favorites were found with the input criteria",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "favorites_id", 
              allowEmptyValue = false, 
              required = true, 
              description = "The favorites id")
      }
  )
  
  @GetMapping(value = "/favorites-by-favorites-id")
  @ResponseStatus(code = HttpStatus.OK)
  List<Favorites> fetchFavoritesByFavoritesId(
      @RequestParam(required = true) 
        int favoritesId);
  
}
