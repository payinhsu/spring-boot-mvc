/**
 * CateDao.java created 2017年2月23日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.dao;

import java.util.List;

import com.poseitech.dao.hibernate.GenericDao;
import com.yolo.member.promotions.entity.DsCate;

/**
 * dao interface
 * @author mango
 */
public interface DsCateDao extends GenericDao<DsCate, Long>{

   /**
    * 依條件查詢分類
    * @param caseId
    * @param activtyId
    * @return  List<DsCate>
    */
   public List<DsCate> findDsCateByCaseId(Long caseId,Long activtyId);
}
