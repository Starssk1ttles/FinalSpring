/**
 * 
 */
package com.promineo.crypto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineo.crypto.dao.CryptoListDao;
import com.promineo.crypto.entity.CryptoList;
import lombok.extern.slf4j.Slf4j;

/**
 * @author north
 *
 */
@Service
@Slf4j
public class DefaultCryptoListService implements CryptoListService {
  @Autowired private CryptoListDao cryptoListDao;
  @Override
  public CryptoList createNewCryptoList(String cryptoListName) {
    return cryptoListDao.createNewCryptoList(cryptoListName);
  }
 
@Override
public CryptoList getCryptoList(String cryptoListName) {
 return cryptoListDao.fetchCryptoList(cryptoListName);
}
  @Override
  public CryptoList updateCryptoListName(String cryptoListName, String newCryptoListName) {
    CryptoList currentCryptoListName = cryptoListDao.fetchCryptoList(cryptoListName);
    CryptoList cryptoListPK = getCryptoListPK(currentCryptoListName);
    return cryptoListDao.updateCryptoListName(cryptoListPK.getId(), newCryptoListName);
  }
  @Override
  public void deleteCryptoList(String cryptoListName) {
    cryptoListDao.deleteCryptoListName(cryptoListName);
  }
  private CryptoList getCryptoListPK(CryptoList currentCryptoListName) {
    return cryptoListDao.getCryptoListPK(currentCryptoListName);
  }
}
