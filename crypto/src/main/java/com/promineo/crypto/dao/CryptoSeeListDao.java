/**
 * 
 */
package com.promineo.crypto.dao;

import java.util.List;
import com.promineo.crypto.entity.Crypto;
import com.promineo.crypto.entity.CryptoList;
import com.promineo.crypto.entity.CryptoSeeList;

/**
 * @author north
 *
 */
public interface CryptoSeeListDao {

    CryptoSeeList saveSymbolsToCryptoList(long cryptoListFK, String symbol);
    void deleteSymbolFromCryptoList(long cryptoListFK, String cryptoSymbol);
    Crypto fetchSymbol(String request);
    CryptoList fetchCryptoListFK(String request);
    List<CryptoSeeList> checkCryptoSeeList(long cryptoListFK);
    }
