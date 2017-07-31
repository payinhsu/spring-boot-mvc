/**
 * ItemDao.java created 2017年2月23日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.dao;

import java.util.List;

import com.poseitech.dao.hibernate.GenericDao;
import com.yolo.member.promotions.entity.DsItem;

/**
 * dao interface
 * @author mango
 */
public interface DsItemDao extends GenericDao<DsItem, Long>{

   /**
    * 依分類代碼查詢品項淸單
    * @param cateId
    * @return List<DsItem> 
    */
   public List<DsItem> findItemByCateId(Long cateId);
}
