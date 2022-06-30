/**
 * 
 */
package com.promineo.crypto.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineo.crypto.entity.CryptoRequest;
import com.promineo.crypto.entity.CryptoSeeList;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * @author north
 *
 */


@OpenAPIDefinition(info = @Info(title = "Add to list"), servers = {
@Server(url = "Http://localhost:8080", description = "Local server.")})
@RequestMapping("/cryptoseelist")
public interface CryptoSeeListController {
  
   @Operation(
      summary = "Create list",
      description = "Build lists of crypto in a list",
      responses = {
          @ApiResponse(responseCode = "201", 
              description = " Symbols are added", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = CryptoSeeList.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "No criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "An unplanned error occured.", 
              content = @Content(mediaType = "application/json"))
      }

   )  
  @PostMapping
  @ResponseBody
  @ResponseStatus(code = HttpStatus.CREATED)  
  CryptoSeeList addSymbolsToCryptoList(CryptoRequest addRequest);
  
  @Operation(
      summary = "Delete a symbol from cryptolist",
      description = "Remove crypto in list",
      responses = {
          @ApiResponse(responseCode = "201", 
              description = " Symbols deleted", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = CryptoSeeList.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "No found criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "Error occured.", 
              content = @Content(mediaType = "application/json"))
      }

   )
  @DeleteMapping
  @ResponseBody
  @ResponseStatus(code = HttpStatus.CREATED)   
  void deleteSymbolFromCryptoList(CryptoRequest deleteRequest);
  
  @Operation(
      summary = "Delete a symbol from cryptolist",
      description = "Remove crypto in cryptolist",
      responses = {
          @ApiResponse(responseCode = "201", 
              description = " Deleted", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = CryptoSeeList.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "No criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "Error occured.", 
              content = @Content(mediaType = "application/json"))
      }

   )
  @GetMapping
  @ResponseBody
  @ResponseStatus(code = HttpStatus.OK)
  List<CryptoSeeList> createdCryptoSeeList(CryptoRequest readRequest); }