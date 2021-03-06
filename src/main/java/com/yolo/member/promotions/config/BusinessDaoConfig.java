/**
 * BusinessDaoConfig.java created 2017年2月26日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 商務邏輯dao設定
 * @author mango
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.yolo.business.dao" })
public class BusinessDaoConfig {


   private static final Logger log = LoggerFactory.getLogger(BusinessDaoConfig.class);

   @Autowired
   private Environment env;

   @PostConstruct
   public void init() {
      log.debug("== Application: " + env.getProperty("spring.application.name") + ", Profiles: "
            + StringUtils.join(env.getActiveProfiles(), ", ") + " ==");
      log.debug("== DB URL: " + env.getProperty("spring.datasource.business.url"));
   }

   @Bean(name = "businessDataSource")
   @ConfigurationProperties(prefix = "spring.datasource.business")
   public DataSource businessDataSource() {
      return DataSourceBuilder.create().build();
   }

   @Bean(name = "businessSessionFactory")
   public LocalSessionFactoryBean businessSessionFactory() {
      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
      sessionFactory.setDataSource(businessDataSource());
      sessionFactory.setPackagesToScan(new String[] { "com.yolo.business.entity" });
      sessionFactory.setMappingLocations(new ClassPathResource("hbm/sql-query.hbm.xml"));
      return sessionFactory;
   }

   @Bean(name = "businessTransactionManager")
   public HibernateTransactionManager businessTransactionManager() {
      HibernateTransactionManager txManager = new HibernateTransactionManager();
      txManager.setSessionFactory(businessSessionFactory().getObject());
      return txManager;
   }

   @Bean
   @Qualifier("businessJdbcTemplate")
   public PersistenceExceptionTranslationPostProcessor businessExceptionTranslation() {
      return new PersistenceExceptionTranslationPostProcessor();
   }
}
