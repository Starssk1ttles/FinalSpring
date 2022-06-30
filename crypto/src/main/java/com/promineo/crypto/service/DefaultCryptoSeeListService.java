/**
 * 
 */
package com.promineo.crypto.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineo.crypto.dao.CryptoSeeListDao;
import com.promineo.crypto.entity.Crypto;
import com.promineo.crypto.entity.CryptoList;
import com.promineo.crypto.entity.CryptoRequest;
import com.promineo.crypto.entity.CryptoSeeList;
import lombok.extern.slf4j.Slf4j;

/**
 * @author north
 *
 */
@Service
@Slf4j
public class DefaultCryptoSeeListService implements CryptoSeeListService {
  @Autowired
  private CryptoSeeListDao cryptoSeeListDao;
  @Transactional
  @Override public CryptoSeeList addSymbolsToCryptoList(CryptoRequest addRequest) {
    CryptoList cryptoListFK = getCryptoListFK(addRequest);
    Crypto cryptoSymbol = getSymbol(addRequest);
    log.debug("Returns cryptoListFK={} symbolName={}", cryptoListFK, cryptoSymbol);
    return cryptoSeeListDao.saveSymbolsToCryptoList(cryptoListFK.getId(), cryptoSymbol.getSymbolPK());
  }
 public void deleteSymbolFromCryptoList(CryptoRequest deleteRequest) {
   CryptoList cryptoListFK = getCryptoListFK(deleteRequest);
   Crypto cryptoSymbol = getSymbol(deleteRequest);
   log.debug("Return cryptoListFK={} symbolName={}", cryptoListFK, cryptoSymbol);
   cryptoSeeListDao.deleteSymbolFromCryptoList(cryptoListFK.getId(), cryptoSymbol.getSymbolPK());
   System.out.println("Symbol " + cryptoSymbol + "is deleted.");
 }
 @Override public List<CryptoSeeList> checkCryptoList(CryptoRequest readRequest) {
   CryptoList cryptoListFK = getCryptoListFK(readRequest);
   return cryptoSeeListDao.checkCryptoSeeList(cryptoListFK.getId());
 }
 private CryptoList getCryptoListFK(CryptoRequest request) {
   if(request.getCryptoListName().isEmpty()) {
     throw new NoSuchElementException("Does not exist");
   }
   return cryptoSeeListDao.fetchCryptoListFK(request.getCryptoListName());
 }
 private Crypto getSymbol(CryptoRequest request) {
   if(request.getSymbol().isEmpty()) {
     throw new NoSuchElementException("Symbol not found");
   }
   return cryptoSeeListDao.fetchSymbol(request.getSymbol());
 }
}
