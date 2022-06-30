/**
 * 
 */
package com.promineo.crypto.dao;

import com.promineo.crypto.entity.CryptoList;

/**
 * @author north
 *
 */
public interface CryptoListDao {
 CryptoList createNewCryptoList(String cryptoListName);
 CryptoList updateCryptoListName(long cryptoListPK, String newCryptoListName);
 CryptoList fetchCryptoList(String cryptoListName);
 CryptoList getCryptoListPK(CryptoList currentCryptoListName);
 void deleteCryptoListName(String cryptoListName);
}
