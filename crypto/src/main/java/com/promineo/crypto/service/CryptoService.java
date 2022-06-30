/**
 * 
 */
package com.promineo.crypto.service;

import java.util.List;
import com.promineo.crypto.entity.BlockChains;
import com.promineo.crypto.entity.Crypto;

/**
 * @author north
 *
 */
public interface CryptoService {
List<Crypto> getCrypto (BlockChains blockChains);
}
