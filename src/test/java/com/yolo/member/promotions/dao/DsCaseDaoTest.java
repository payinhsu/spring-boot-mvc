/**
 * DsCaseDaoTest.java created 2017年2月28日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yolo.member.promotions.config.MicroserviceApplication;
import com.yolo.member.promotions.entity.DsCase;

/**
 *
 * @author mango
 */
@Commit
@Rollback(true)
@Transactional(transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MicroserviceApplication.class, initializers = ConfigFileApplicationContextInitializer.class)
public class DsCaseDaoTest {
   
   private Logger log = LoggerFactory.getLogger(getClass());
   

}
