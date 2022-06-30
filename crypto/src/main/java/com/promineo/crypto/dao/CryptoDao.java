/**
 * 
 */
package com.promineo.crypto.dao;

import java.util.List;
import com.promineo.crypto.entity.BlockChains;
import com.promineo.crypto.entity.Crypto;

/**
 * @author north
 *
 */
public interface CryptoDao {
 public List<Crypto> getCryptoList(BlockChains blockchain);
}
