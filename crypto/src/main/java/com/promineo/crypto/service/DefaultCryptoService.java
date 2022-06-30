/**
 * 
 */
package com.promineo.crypto.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineo.crypto.dao.CryptoDao;
import com.promineo.crypto.entity.BlockChains;
import com.promineo.crypto.entity.Crypto;

/**
 * @author north
 *
 */
@Service
public class DefaultCryptoService implements CryptoService {
@Autowired private CryptoDao cryptoDao;
@Override
public List<Crypto> getCrypto(BlockChains blockChains) {
  return cryptoDao.getCryptoList(blockChains);
}
}
