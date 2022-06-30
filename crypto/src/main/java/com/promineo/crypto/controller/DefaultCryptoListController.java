/**
 * 
 */
package com.promineo.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineo.crypto.entity.CryptoList;
import com.promineo.crypto.service.CryptoListService;

/**
 * @author north
 *
 */
@RestController
public class DefaultCryptoListController implements CryptoListController {
@Autowired
private CryptoListService cryptoListService;

@Override
public CryptoList createNewCryptoList(String cryptoListName) {
  return cryptoListService.createNewCryptoList(cryptoListName);
}
@Override public CryptoList getCryptoList(String cryptoListName) {
  
  return cryptoListService.getCryptoList(cryptoListName);
}
@Override public CryptoList updateCryptoListName(String cryptoListName, String newCryptoListName) {
  return cryptoListService.updateCryptoListName(cryptoListName, newCryptoListName);
  }
@Override public void deleteCryptoList(String cryptoListName) {
  cryptoListService.deleteCryptoList(cryptoListName);
}

}
