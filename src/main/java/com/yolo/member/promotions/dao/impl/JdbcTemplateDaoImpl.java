/**
 * JdbcTemplateDaoImpl.java created 2017年3月3日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yolo.member.promotions.dao.JdbcTemplateDao;
import com.yolo.member.promotions.vo.RecordVo;

/**
 *
 * @author mango
 */
@Repository
public class JdbcTemplateDaoImpl implements JdbcTemplateDao {
   private static Logger log = LoggerFactory.getLogger(JdbcTemplateDaoImpl.class);

   @Autowired
   private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
   
   /**
    * 查詢中獎淸單
    * @param pLimit
    * @return  List<RecordVo>
    */
   public List<RecordVo> findNewVoList(Integer pLimit){
      StringBuffer joinSqlSb = new StringBuffer();
      joinSqlSb.append(" select ");
      joinSqlSb.append(" r.record_id as recordId ");
      joinSqlSb.append(" ,r.user_id ");
      joinSqlSb.append(" ,r.user_name as userName ");
      joinSqlSb.append(" ,r.sign_date as signDate ");
      joinSqlSb.append(" ,DATE_FORMAT(r.sign_date,'%m.%d') as signDateStr ");
      joinSqlSb.append(" ,r.item_id as itemId ");
      joinSqlSb.append(" , (select name from ds_item i where i.item_id = r.item_id ) as itemName ");
      joinSqlSb.append(" from ds_record r ");
      joinSqlSb.append(" order by r.record_id desc ");
      joinSqlSb.append(" limit " + pLimit);
      log.debug("joinSqlSb = {}",joinSqlSb );
      return namedParameterJdbcTemplate.query(joinSqlSb.toString(),new BeanPropertyRowMapper<RecordVo>(RecordVo.class));
   }
}
