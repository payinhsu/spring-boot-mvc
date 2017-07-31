/**
 * CaseDaoImpl.java created 2017年2月23日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.poseitech.dao.hibernate.GenericHibernateDao;
import com.yolo.member.promotions.dao.DsCaseDao;
import com.yolo.member.promotions.entity.DsCase;

/**
 *
 * @author mango
 */
@Repository
public class DsCaseDaoImpl extends GenericHibernateDao<DsCase, Long> implements DsCaseDao {

   private Logger log = LoggerFactory.getLogger(getClass());
   
   @SuppressWarnings("unchecked")
   public List<DsCase> getAll(){
      log.info("dao getALL");
      Criteria criteria = this.getSession().createCriteria(DsCase.class);
      List<DsCase> list = (List<DsCase>)criteria.list();
      return list;
   }
}
