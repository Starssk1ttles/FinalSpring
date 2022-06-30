/**
 * 
 */
package com.promineo.crypto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineo.crypto.entity.BlockChains;
import com.promineo.crypto.entity.Crypto;
import com.promineo.crypto.service.CryptoService;

/**
 * @author north
 *
 */
@RestController
public class DefaultCryptoController implements CryptoController {
  
  @Autowired
  private CryptoService cryptoService;
  public List<Crypto> getCrypto(BlockChains blockChains) {
    return cryptoService.getCrypto(blockChains);
  }

}
