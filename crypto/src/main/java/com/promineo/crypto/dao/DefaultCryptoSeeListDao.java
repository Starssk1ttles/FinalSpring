/**
 * 
 */
package com.promineo.crypto.dao;

import java.security.InvalidParameterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.promineo.crypto.entity.BlockChains;
import com.promineo.crypto.entity.Crypto;
import com.promineo.crypto.entity.CryptoList;
import com.promineo.crypto.entity.CryptoSeeList;

/**
 * @author north
 *
 */
@Component
public class DefaultCryptoSeeListDao implements CryptoSeeListDao {
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public CryptoSeeList saveSymbolsToCryptoList(long cryptoListFK, String symbol) {
    SqlParams sqlParams = generateAddSymbolToCryptoListSql(cryptoListFK, symbol);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(sqlParams.sql, sqlParams.source, keyHolder);
    if(jdbcTemplate.update(sqlParams.sql, sqlParams.source) == 0) {
      throw new InvalidParameterException("NOT FOUND");
    }
    Long CryptoSeeListId = keyHolder.getKey().longValue();
    return CryptoSeeList.builder()
            .id(CryptoSeeListId)
            .cryptoListFK(cryptoListFK)
            .symbol(symbol)
            .build();
  }
  private SqlParams generateAddSymbolToCryptoListSql(long cryptoListFK, String symbol) {
    SqlParams params = new SqlParams();
    params.sql = ""
        +"INSERT INTO cryptoseelist ("
        +"cryptolist_fk, crypto_symbol"
        +") VALUES ("
        +":cryptoListId, :crypto_symbol"
        +")";
    params.source.addValue("cryptoListId", cryptoListFK);
    params.source.addValue("crypto_symbol", symbol);
    return params;
        
  }
  
  @Override public void deleteSymbolFromCryptoList(long cryptoListFK, String cryptoSymbol) {
    String sql =""
        +"DELETE FROM cryptoseelist "
        +"WHERE cryptolist_fk =:cryptolistFK "
        +"AND crypto_symbol =:symbol";
    Map<String, Object> params = new HashMap<>();
    params.put("cryptolistFK", cryptoListFK);
    params.put("symbol", cryptoSymbol);
    jdbcTemplate.update(sql, params);
  }
  public List<CryptoSeeList> checkCryptoSeeList(long cryptoListFK) {
    SqlParams sqlParams = new SqlParams(); 
    sqlParams.sql =""
    +"SELECT cryptolist.name, crypto.symbol "
    +"FROM cryptolist, cryptoseelist, crypto "
    +"WHERE cryptolist_pk = cryptoseelist.cryptolist_fk "
    +"AND cryptoseelist.cryptolist_fk =:cryptolistFK "
    +"AND cryptoseelist.crypto_symbol = crypto.symbol";
    
    Map<String, Object> params = new HashMap<>();
    params.put("cryptolistFK", cryptoListFK);
    return jdbcTemplate.query(sqlParams.sql, params, new RowMapper<>() {
      @Override
      public CryptoSeeList mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CryptoSeeList.builder()
            .symbol(rs.getString("symbol"))
            .build();
      }});
    }
    
  
  
  @Override
  public Crypto fetchSymbol(String symbol) {
    String sql =""
        +"SELECT * FROM crypto "
        +"WHERE symbol =:symbolPK";
    Map<String, Object> params = new HashMap<>();
    params.put("symbolPK", symbol);
    return jdbcTemplate.query(sql, params, new CryptoResultSetExtractor());
    
}
  @Override
  public CryptoList fetchCryptoListFK(String request) {
    String sql = ""
        +"SELECT * FROM "
        +"cryptolist WHERE "
        +"name = :cryptoListName";
    Map<String, Object> params = new HashMap<>();
    params.put("cryptoListName", request);
    return jdbcTemplate.query(sql, params, new CryptoListResultSetExtractor());
  }
  class CryptoResultSetExtractor implements ResultSetExtractor<Crypto> {
    @Override
    public Crypto extractData(ResultSet rs) throws SQLException{
      rs.next();
      return Crypto.builder()
              .symbolPK(rs.getString("symbol"))
              .blockchainId(BlockChains.valueOf(rs.getString("blockchain_id")))
              .name(rs.getString("name"))
              .currentPrice(rs.getFloat("currentprice"))
              .build();
    }
  }
  class CryptoListResultSetExtractor implements ResultSetExtractor<CryptoList> {
    @Override
    public CryptoList extractData(ResultSet rs) throws SQLException {
      rs.next();
      return CryptoList.builder()
             .id(rs.getLong("cryptolist_pk"))
             .name(rs.getString("name"))
             .build();
    }
  }
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}
  
