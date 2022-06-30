/**
 * 
 */
package com.promineo.crypto.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineo.crypto.entity.BlockChains;
import com.promineo.crypto.entity.Crypto;
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
@OpenAPIDefinition(info = @Info(title = "Crypto symbols"), servers = {
     @Server(url = "Http://localhost:8080", description = "Local server.")})
 @RequestMapping("/crypto")
public interface CryptoController {
  @Operation(
      summary = "Returns a list of crypto symbols",
      description = "Returns a list of crypto symbols",
      responses = {
          @ApiResponse(responseCode = "200", 
              description = " A list of crypto is returned.", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Crypto.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "No criteria.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "Error occured.", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = { 
          @Parameter(name = "blockchain", 
              allowEmptyValue = false, 
              required = false, 
              description = "Blockchain name"),         
      }
   )  
  
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Crypto> getCrypto(@RequestParam BlockChains blockchains);
}

