/**
 * SkyUserDepositDao.java created 2017年3月3日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.business.dao;

import java.util.Date;
import java.util.List;

import com.poseitech.dao.hibernate.GenericDao;
import com.yolo.business.entity.SkyUserDeposit;

/**
 *
 * @author mango
 */
public interface SkyUserDepositDao  extends GenericDao<SkyUserDeposit,Long> {

   /**
    * 活動時間內，查詢循環天數區間的儲值淸單
    * @param pUserId
    * @param pActivtyStartDate
    * @param pActivtyEndDate
    * @param cycleParam
    * @return List<SkyUserDeposit>
    */
   public List<SkyUserDeposit> findUserDepositByActivty(Integer pUserId,Date pActivtyStartDate,Date pActivtyEndDate,int cycleParam);
}
