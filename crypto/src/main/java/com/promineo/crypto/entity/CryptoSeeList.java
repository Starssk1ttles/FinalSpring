/**
 * 
 */
package com.promineo.crypto.entity;

import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

/**
 * @author north
 *
 */

@Data
@Builder
public class CryptoSeeList {
private long id;
private long cryptoListFK;

@Nullable private String symbol;
@JsonIgnore
public Long id() {
  return id;
}
}
