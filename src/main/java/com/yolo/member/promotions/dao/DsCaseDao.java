/**
 * CaseDao.java created 2017年2月23日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.dao;

import java.util.List;

import com.poseitech.dao.hibernate.GenericDao;
import com.yolo.member.promotions.entity.DsCase;

/**
 * dao interface
 * @author mango
 */
public interface DsCaseDao extends GenericDao<DsCase, Long>{

   public List<DsCase> getAll();
}
