/**
 * 
 */
package com.promineo.crypto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineo.crypto.entity.CryptoList;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * @author north
 *
 */
@OpenAPIDefinition(info = @Info(title = "Crypto List"), servers = {
    @Server(url = "Http://localhost:8080", description = "Local server.")})
@RequestMapping("/cryptolist")
public interface CryptoListController {
  @Operation(
      summary = "Creates a new list",
      description = "Creates a new list for the user",
      responses = {
          @ApiResponse(responseCode = "200", 
              description = "List created", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = CryptoList.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "Invalid List name.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "Not a valid input.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "Error occured.", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = { 
          @Parameter(name = "cryptoListtName", 
              allowEmptyValue = false, 
              required = false, 
              description = "New List."),
      }
   )
  //@formatter:on
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public CryptoList createNewCryptoList(@RequestParam String cryptoListName);
  
  
//@formatter:off
  @Operation(
      summary = "Returns a cryptolist",
      description = "Returns list",
      responses = {
          @ApiResponse(responseCode = "200", 
              description = "List found and returned.", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = CryptoList.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "Not valid.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "Error occured.", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = { 
          @Parameter(name = "cryptolistName", 
              allowEmptyValue = false, 
              required = false, 
              description = "The list name"),
      }
   )
  //@formatter:on
 
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  CryptoList getCryptoList(@RequestParam String cryptoListName);

//@formatter:off
  @Operation(
      summary = "Rename list",
      description = "Renames list",
      responses = {
          @ApiResponse(responseCode = "200", 
              description = " list renamed.", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = CryptoList.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "Not a valid list.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "Error occured.", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = { 
          @Parameter(name = "cryptolistName", 
              allowEmptyValue = false, 
              required = true, 
              description = "Name"),     
            
          @Parameter(name = "newCryptolistName", 
              allowEmptyValue = false, 
              required = true, 
              description = "New name")      
      }
      
   )

  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  CryptoList updateCryptoListName(@RequestParam String cryptoListName, @RequestParam String newCryptoListName);

  @Operation(
      summary = "Deletes list",
      description = "Delete list",
      responses = {
          @ApiResponse(responseCode = "200", 
              description = " List deleted.", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = CryptoList.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "Invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "Error occured.", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = { 
          @Parameter(name = "cryptolistName", 
              allowEmptyValue = false, 
              required = false, 
              description = "List Name"),
      }
   )
  @DeleteMapping
  @ResponseStatus(code = HttpStatus.OK)
  void deleteCryptoList(@RequestParam String cryptoListName);
}
