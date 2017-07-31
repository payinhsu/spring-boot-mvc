/**
 * ActivtyDaoImpl.java created 2017年2月23日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.poseitech.dao.hibernate.GenericHibernateDao;
import com.poseitech.dao.hibernate.Paging;
import com.yolo.member.promotions.dao.DsActivtyDao;
import com.yolo.member.promotions.entity.DsActivty;

/**
 *
 * @author mango
 */
@Repository
public class DsActivtyDaoImpl extends GenericHibernateDao<DsActivty, Long> implements DsActivtyDao {

private Logger log = LoggerFactory.getLogger(getClass());
   
   @SuppressWarnings("unchecked")
   public List<DsActivty> findDsActivtyList(String pName, Date pStartDate, Date pEndDate, Integer pIsRepeat, Integer pStatus,
         Paging pPaging){
      log.info("dao findDsActivtyList");
      Criteria criteria = this.getSession().createCriteria(DsActivty.class);
      if (StringUtils.isNotBlank(pName)) {
         criteria.add(Restrictions.like("name", "%" + pName + "%"));
      }

      if (pIsRepeat != null) {
         criteria.add(Restrictions.eq("isRepeat", pIsRepeat));
      }

      if (pStatus != null) {
         criteria.add(Restrictions.eq("status", pStatus));
      }

      if (pStartDate != null && pEndDate != null && pEndDate.getTime() > pStartDate.getTime()) {
         criteria.add(Restrictions.gt("startDate", pStartDate));
         criteria.add(Restrictions.lt("endDate", pEndDate));
      }
      if(pPaging != null){
         this.setPaging(criteria, pPaging);
      }
      List<DsActivty> list = (List<DsActivty>)criteria.list();
      return list;
   }
   
   /**
    * 查詢目前有效的簽到活動
    * @return DsActivty
    */
   public DsActivty getVaildSigninActivty(){
      DsActivty result = null;
      StringBuffer activtySqlSb = new StringBuffer();
      activtySqlSb.append(" select * from ds_activty ");
      activtySqlSb.append(" where current_date() between start_date AND end_date ");
      activtySqlSb.append(" and status = 1 ");
      activtySqlSb.append(" and activty_type = 0 ");
      List<DsActivty> dsActivtyList = findBySQL(activtySqlSb.toString());
      if(dsActivtyList != null && dsActivtyList.size() > 0){
         result = dsActivtyList.get(0);
      }
      return result;
   }
}
