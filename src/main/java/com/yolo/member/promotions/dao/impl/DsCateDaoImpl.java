/**
 * CateDaoImpl.java created 2017年2月23日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.poseitech.dao.hibernate.GenericHibernateDao;
import com.yolo.member.promotions.dao.DsCateDao;
import com.yolo.member.promotions.entity.DsCate;

/**
 *
 * @author mango
 */
@Repository
public class DsCateDaoImpl extends GenericHibernateDao<DsCate, Long> implements DsCateDao  {
   
   /**
    * 依條件查詢分類
    * @param caseId
    * @param activtyId
    * @return  List<DsCate>
    */
   public List<DsCate> findDsCateByCaseId(Long caseId,Long activtyId){
      if(caseId == null){
         String msg = String.format("%s: illegal input argument of method", caseId);
         throw new IllegalArgumentException(msg);
      }
      if(activtyId == null){
         String msg = String.format("%s: illegal input argument of method", activtyId);
         throw new IllegalArgumentException(msg);
      }
      StringBuffer cateSqlSb = new StringBuffer();
      cateSqlSb.append(" select * from ds_cate ");
      cateSqlSb.append(" where case_id = ? ");
      cateSqlSb.append(" and activty_id = ? ");
      return findBySQL(cateSqlSb.toString(), caseId,activtyId);
   }
}
