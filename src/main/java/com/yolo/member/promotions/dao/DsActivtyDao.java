/**
 * ActivtyDao.java created 2017年2月23日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.dao;

import java.util.Date;
import java.util.List;

import com.poseitech.dao.hibernate.GenericDao;
import com.poseitech.dao.hibernate.Paging;
import com.yolo.member.promotions.entity.DsActivty;

/**
 * dao interface
 * @author mango
 */
public interface DsActivtyDao extends GenericDao<DsActivty, Long> {

   public List<DsActivty> findDsActivtyList(String pName, Date pStartDate, Date pEndDate, Integer pIsRepeat, Integer pStatus,
         Paging pPaging);
   
   /**
    * 查詢目前有效的簽到活動
    * @return DsActivty
    */
   public DsActivty getVaildSigninActivty();
}
