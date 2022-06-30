/**
 * 
 */
package com.promineo.crypto.service;

import com.promineo.crypto.entity.CryptoList;

/**
 * @author north
 *
 */
public interface CryptoListService {
 CryptoList createNewCryptoList(String cryptoListName);
 CryptoList getCryptoList(String cryptoListName);
 CryptoList updateCryptoListName(String cryptoListName, String newCryptoListName);
 void deleteCryptoList(String cryptoListName);
}
