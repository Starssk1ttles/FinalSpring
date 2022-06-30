/**
 * 
 */
package com.promineo.crypto.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author north
 *
 */
@Data
@Builder
public class Crypto {
  private String symbolPK;
  private BlockChains blockchainId;
  private String name;
  private double currentPrice;
}
