/**
 * ItemDaoImpl.java created 2017年2月23日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.poseitech.dao.hibernate.GenericHibernateDao;
import com.yolo.member.promotions.dao.DsItemDao;
import com.yolo.member.promotions.entity.DsItem;

/**
 *
 * @author mango
 */
@Repository
public class DsItemDaoImpl extends GenericHibernateDao<DsItem, Long> implements DsItemDao {

   /**
    * 依分類代碼查詢品項淸單
    * @param cateId
    * @return List<DsItem> 
    */
   public List<DsItem> findItemByCateId(Long cateId){
      if(cateId == null){
         String msg = String.format("%s: illegal input argument of method", cateId);
         throw new IllegalArgumentException(msg);
      }
      StringBuffer cateSqlSb = new StringBuffer();
      cateSqlSb.append(" select * from ds_item ");
      cateSqlSb.append(" where cate_id = ? ");
      return findBySQL(cateSqlSb.toString(), cateId);
   }
}
