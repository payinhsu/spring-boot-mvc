/**
 * RecordDaoImpl.java created 2017年2月23日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.poseitech.dao.hibernate.GenericHibernateDao;
import com.poseitech.dao.hibernate.Paging;
import com.yolo.member.promotions.dao.DsRecordDao;
import com.yolo.member.promotions.entity.DsRecord;

/**
 *
 * @author mango
 */
@Repository
public class DsRecordDaoImpl extends GenericHibernateDao<DsRecord, Long> implements DsRecordDao {

   /**
    * 依使用者代碼查詢今日是否有簽到
    * @param pUserId
    * @return true:有；false:沒有
    */
   public boolean isSigninByUser(Long pUserId){
      if(pUserId == null){
         String msg = String.format("%s: illegal input argument of method", pUserId);
         throw new IllegalArgumentException(msg);
      }
      boolean result = false;
      StringBuffer recordSqlSb = new StringBuffer();
      recordSqlSb.append(" select * from ds_record ");
      recordSqlSb.append(" where user_id = " + pUserId);
      recordSqlSb.append(" and sign_date between current_date() ");
      recordSqlSb.append(" AND DATE_ADD(CURDATE(), INTERVAL 1 DAY); ");
      List<DsRecord> list = findBySQL(recordSqlSb.toString());
      if(list != null && list.size() > 0){
         result = true;
      }
      return result;
   }
   
   /**
    * 依使用者代碼查詢最新簽到記錄
    * @param pUserId
    * @return DsRecord
    */
   public DsRecord getNewSigninByUser(Long pUserId){
      if(pUserId == null){
         String msg = String.format("%s: illegal input argument of method", pUserId);
         throw new IllegalArgumentException(msg);
      }
      DsRecord result = null;
      StringBuffer recordSqlSb = new StringBuffer();
      recordSqlSb.append(" select * from ds_record ");
      recordSqlSb.append(" where user_id = " + pUserId);
      recordSqlSb.append(" order by create_date desc ");
      List<DsRecord> dsRecordList = findBySQL(recordSqlSb.toString());
      if(dsRecordList != null && dsRecordList.size() > 0){
         result = dsRecordList.get(0);
      }
      return result;
   }
   
   /**
    * 依使用者代碼和活動代碼查詢簽到筆數
    * @param pUserId
    * @param pActivtyId
    * @return Long
    */
   public Long countByUserIdAndActivtyId(Long pUserId,Long pActivtyId){
      if(pUserId == null){
         String msg = String.format("%s: illegal input argument of method", pUserId);
         throw new IllegalArgumentException(msg);
      }
      if(pActivtyId == null){
         String msg = String.format("%s: illegal input argument of method", pActivtyId);
         throw new IllegalArgumentException(msg);
      }
      StringBuffer recordSqlSb = new StringBuffer();
      recordSqlSb.append(" select * from ds_record ");
      recordSqlSb.append(" where user_id = ? ");
      recordSqlSb.append(" and activty_id = ? ;");
      List<DsRecord> list = findBySQL(recordSqlSb.toString(),pUserId,pActivtyId);
      if(list != null && list.size() > 0){
         return (long) list.size();
      }else{
         return 0L;
      }
   }
   
   /**
    * 取最近的淸單
    * @param pLimit 最新的筆數
    * @return List<DsRecord>
    */
   public List<DsRecord> findNewList(Integer pLimit){
      if(pLimit == null){
         String msg = String.format("%s: illegal input argument of method", pLimit);
         throw new IllegalArgumentException(msg);
      }
      Paging paging = new Paging();
      paging.setDir("DESC");
      paging.setLimit(pLimit);
      return findAll(paging);
   }
}
