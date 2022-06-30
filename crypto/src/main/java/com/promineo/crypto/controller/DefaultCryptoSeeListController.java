/**
 * 
 */
package com.promineo.crypto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineo.crypto.entity.CryptoRequest;
import com.promineo.crypto.entity.CryptoSeeList;
import com.promineo.crypto.service.CryptoSeeListService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author north
 *
 */

@RestController
@Slf4j
public class DefaultCryptoSeeListController implements CryptoSeeListController {
  @Autowired
  private CryptoSeeListService cryptoSeeListService;
  
  @Override
  public CryptoSeeList addSymbolsToCryptoList(CryptoRequest addRequest) {
    log.debug("cryptoRequest={}", addRequest);
    return cryptoSeeListService.addSymbolsToCryptoList(addRequest);
  }
  @Override
  public void deleteSymbolFromCryptoList(CryptoRequest deleteRequest) {
    log.debug("cryptoRequest={}", deleteRequest);
    cryptoSeeListService.deleteSymbolFromCryptoList(deleteRequest);
  }
  @Override
  public List<CryptoSeeList> createdCryptoSeeList(CryptoRequest readRequest) {
    return cryptoSeeListService.checkCryptoList(readRequest);
  }
}
