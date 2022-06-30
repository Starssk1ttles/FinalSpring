/**
 * 
 */
package com.promineo.crypto.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.promineo.crypto.entity.CryptoList;

/**
 * @author north
 *
 */
@Component
@Service
public class DefaultCryptoListDao implements CryptoListDao {
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  
  @Override
  public CryptoList createNewCryptoList(String cryptoListName) {
    SqlParams sqlParams = generateNewCryptoListSql(cryptoListName);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(sqlParams.sql, sqlParams.source, keyHolder);
    
    Long cryptoListPK = keyHolder.getKey().longValue();
    
    return CryptoList.builder()
            .id(cryptoListPK)
            .name(cryptoListName)
            .build();
  }
  private SqlParams generateNewCryptoListSql(String cryptoListName ) {
    SqlParams params = new SqlParams();
    params.sql = ""
        +"INSET INTO cryptolist "
        +"(name"
        +") VALUES ("
        +"cryptoListName)";
    params.source.addValue("cryptoListName", cryptoListName);
    return params;
  }
  @Override 
  public CryptoList updateCryptoListName(long cryptoListPK, String newCryptoListName) {
    String sql=""
        +"UPDATE cryptolist "
        +"SET name =:newCryptoListName "
        +"WHERE cryptolist_PK = :cryptoListPK";
    
    Map<String, Object> params = new HashMap<>();
    params.put("newCryptoListName", newCryptoListName);
    params.put("cryptoListPK", cryptoListPK);
    jdbcTemplate.update(sql, params);
    return CryptoList.builder()
            .id(cryptoListPK)
            .name(newCryptoListName)
            .build();
    }
  @Override public void deleteCryptoListName(String cryptoListName) {
    String sql =""
        +"DELETE FROM cryptolist "
        +"WHERE name= :cryptoListName";
    Map<String, Object> params = new HashMap<>();
    params.put("cryptoListName", cryptoListName);
    if (jdbcTemplate.update(sql, params) !=1 ) {throw new NoSuchElementException();}
    }
    @Override public CryptoList fetchCryptoList(String cryptoListName) {
      String sql =""
          +"SELECT * FROM "
          +"cryptolist WHERE "
          +"name = :cryptoListName";
      Map<String, Object> params = new HashMap<>();
      params.put("cryptoListName", cryptoListName);
      return jdbcTemplate.query(sql, params, new CryptoListResultSetExtractor());
    }
    @Override
    public CryptoList getCryptoListPK(CryptoList currentCryptoListName) {
      String sql =""
          +"SELECT * FROM "
          +"cryptolist WHERE "
          +"name = :cryptoListName";
      Map<String, Object> params = new HashMap<>();
      params.put("cryptoListName", currentCryptoListName);
      return CryptoList.builder()
              .id(currentCryptoListName.getId())
              .build();
    }
    class CryptoListResultSetExtractor implements ResultSetExtractor<CryptoList> {
      @Override public CryptoList extractData(ResultSet rs) throws SQLException {
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
  
 
