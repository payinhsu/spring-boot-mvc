/**
 * SkyUserDepositImpl.java created 2017年3月3日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.business.dao.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.yolo.business.dao.SkyUserDepositDao;
import com.yolo.business.dao.hibernate.BusinessGenericHibernateDao;
import com.yolo.business.entity.SkyUserDeposit;

/**
 *
 * @author mango
 */
@Repository
public class SkyUserDepositDaoImpl extends BusinessGenericHibernateDao<SkyUserDeposit, Long> implements SkyUserDepositDao {
   private static Logger log = LoggerFactory.getLogger(SkyUserDepositDaoImpl.class);
   
   /**
    * 活動時間內，查詢循環天數區間的儲值淸單
    * @param pUserId
    * @param pActivtyStartDate
    * @param pActivtyEndDate
    * @param cycleParam
    * @return List<SkyUserDeposit>
    */
   public List<SkyUserDeposit> findUserDepositByActivty(Integer pUserId,Date pActivtyStartDate,Date pActivtyEndDate,int cycleParam){
      List<SkyUserDeposit> result = null;
      //活動時間內，查詢循環天數區間的儲值淸單
      StringBuffer depsitSqlSb = new StringBuffer();
      depsitSqlSb.append(" select * from sky_user_deposit ");
      depsitSqlSb.append(" where status = 1 ");
      depsitSqlSb.append(" and user_id = ? ");
      depsitSqlSb.append(" and (from_unixtime(create_time) between ? and ?) ");
      depsitSqlSb.append(" and (from_unixtime(create_time)  ");
      depsitSqlSb.append("  between DATE_ADD(CURDATE(), INTERVAL ? DAY) ");
      depsitSqlSb.append("  and DATE_ADD(CURDATE(), INTERVAL -1 DAY) ) ");
      depsitSqlSb.append(" order by create_time desc ");
      log.debug("depsitSqlSb = {}",depsitSqlSb);
      result = findBySQL(depsitSqlSb.toString(),pUserId,pActivtyStartDate,pActivtyEndDate,(cycleParam+1)*(-1));
      return result;
   }
}
