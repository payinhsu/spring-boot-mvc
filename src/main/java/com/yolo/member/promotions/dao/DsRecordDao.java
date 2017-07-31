/**
 * RecordDao.java created 2017年2月23日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.dao;

import java.util.List;

import com.poseitech.dao.hibernate.GenericDao;
import com.yolo.member.promotions.entity.DsRecord;

/**
 * dao interface
 * @author mango
 */
public interface DsRecordDao extends GenericDao<DsRecord, Long>{

   /**
    * 依使用者代碼查詢今日是否有簽到;預設false
    * @param pUserId 使用者代碼
    * @return true:有；false:沒有.
    */
   public boolean isSigninByUser(Long pUserId);
   
   /**
    * 依使用者代碼查詢最新簽到記錄
    * @param pUserId
    * @return DsRecord
    */
   public DsRecord getNewSigninByUser(Long pUserId);
   
   /**
    * 取最近的淸單
    * @param pLimit 最新的筆數
    * @return List<DsRecord>
    */
   public List<DsRecord> findNewList(Integer pLimit);
   
   /**
    * 依使用者代碼和活動代碼查詢簽到筆數
    * @param pUserId
    * @param pActivtyId
    * @return Long
    */
   public Long countByUserIdAndActivtyId(Long pUserId,Long pActivtyId);
}
