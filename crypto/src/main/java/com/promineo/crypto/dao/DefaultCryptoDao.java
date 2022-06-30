/**
 * 
 */
package com.promineo.crypto.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.promineo.crypto.entity.BlockChains;
import com.promineo.crypto.entity.Crypto;

/**
 * @author north
 *
 */

@Component
@Service
public class DefaultCryptoDao implements CryptoDao {
@Autowired
private NamedParameterJdbcTemplate jdbcTemplate;

@Override
public List<Crypto> getCryptoList(BlockChains blockChains) {
  String sql =""
      +"SELECT * "
      +"FROM crypto "
      +"WHERE blockchain_id = :blockchain_id";
  
  Map<String, Object> params = new HashMap<>();
  params.put("blockchain_id", blockChains.toString());
 return jdbcTemplate.query(sql, params, new RowMapper<>() {
   @Override
   public Crypto mapRow(ResultSet rs, int rowNum) throws SQLException {
     return Crypto.builder()
             .symbolPK(rs.getString("symbol"))
             .blockchainId(BlockChains.valueOf(rs.getString("blockchain_id")))
             .name(rs.getString("name"))
             .currentPrice(rs.getFloat("currentprice"))
             .build();
   }});
   
}
}
