/**
 * BusinessGenericHibernateDao.java created 2017年2月26日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.business.dao.hibernate;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.poseitech.dao.hibernate.AbstractModel;
import com.poseitech.dao.hibernate.GenericHibernateDao;

/**
 *
 * @author mango
 */
@Repository
public abstract class BusinessGenericHibernateDao<T extends AbstractModel<ID>, ID extends Serializable> extends GenericHibernateDao<T, ID> {

   @Autowired
   @Qualifier("businessSessionFactory")
   private SessionFactory SessionFactory;
   
   @PostConstruct
   public void init() {
      super.setSessionFactory(SessionFactory);
   }
}
