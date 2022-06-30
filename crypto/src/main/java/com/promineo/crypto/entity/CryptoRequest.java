/**
 * 
 */
package com.promineo.crypto.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

/**
 * @author north
 *
 */
@Data
public class CryptoRequest {
@NotNull
@Length(max = 25)
@Pattern(regexp = "[\\w\\s]*")
private String cryptoListName;

@NotNull
@Length(max = 6)
@Pattern (regexp = "[\\w\\s]*")
private String symbol;
}
