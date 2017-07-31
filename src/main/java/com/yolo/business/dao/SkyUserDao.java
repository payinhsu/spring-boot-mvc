/**
 * SkyUserDao.java created 2017年3月2日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.business.dao;

import com.poseitech.dao.hibernate.GenericDao;
import com.yolo.business.entity.SkyUser;

/**
 *
 * @author mango
 */
public interface SkyUserDao extends GenericDao<SkyUser,Long> {

   /**
    * 查詢使用者是否存在
    * @param pUserAccount
    * @return boolean
    */
   public boolean isExistUser(String pUserAccount);
   
   /**
    * 依帳號查詢使用者
    * @param pName
    * @return SkyUser
    */
   public SkyUser getUserByName(String pName);
}
