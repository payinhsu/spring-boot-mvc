/**
 * SkyUserDaoImpl.java created 2017年3月2日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.business.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.yolo.business.dao.SkyUserDao;
import com.yolo.business.dao.hibernate.BusinessGenericHibernateDao;
import com.yolo.business.entity.SkyUser;

/**
 *
 * @author mango
 */
@Repository
public class SkyUserDaoImpl extends BusinessGenericHibernateDao<SkyUser, Long> implements SkyUserDao {

   /**
    * 查詢使用者是否存在
    * @param pUserAccount
    * @return boolean
    */
   public boolean isExistUser(String pUserAccount){
      boolean result = false;
      if(StringUtils.isBlank(pUserAccount)){
         String msg = String.format("%s: illegal input argument of method", pUserAccount);
         throw new IllegalArgumentException(msg);
      }
      StringBuffer userSqlSb = new StringBuffer();
      userSqlSb.append(" select counrt(id) from sky_user ");
      userSqlSb.append(" where status = 1 ");
      if(StringUtils.isNotBlank(pUserAccount)){
         userSqlSb.append(" and name = ? ;");
      }
      Long userCount = countBySQL(userSqlSb.toString(),pUserAccount);
      if(userCount > 0){
         result = true;
      }
      return result;
   }
   
   /**
    * 依帳號查詢使用者
    * @param pName
    * @return SkyUser
    */
   public SkyUser getUserByName(String pName){
      SkyUser result = null;
      if(StringUtils.isBlank(pName)){
         String msg = String.format("%s: illegal input argument of method", pName);
         throw new IllegalArgumentException(msg);
      }
      StringBuffer userSqlSb = new StringBuffer();
      userSqlSb.append(" select * from sky_user ");
      userSqlSb.append(" where status = 1 ");
      if(StringUtils.isNotBlank(pName)){
         userSqlSb.append(" and name = ? ;");
      }
      List<SkyUser> skyUserList = findBySQL(userSqlSb.toString(), pName);
      if(skyUserList != null && skyUserList.size() > 0){
         result = skyUserList.get(0);
      }
      return result;      
   }
}
