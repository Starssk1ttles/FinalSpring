/**
 * 
 */
package com.promineo.crypto.service;

import java.util.List;
import com.promineo.crypto.entity.CryptoRequest;
import com.promineo.crypto.entity.CryptoSeeList;

/**
 * @author north
 *
 */
public interface CryptoSeeListService {
  CryptoSeeList addSymbolsToCryptoList(CryptoRequest addRequest);
  void deleteSymbolFromCryptoList(CryptoRequest deleteRequest);
  List<CryptoSeeList> checkCryptoList(CryptoRequest readRequest);
}
