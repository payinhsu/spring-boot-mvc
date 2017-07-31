/** 
 * 
 * 
 * $$LastChangedBy$$
 * $$Date$$
 * $$Revision$$
 *  
 * */
package com.yolo.member.promotions.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.yolo.member.promotions.dao")
public class DaoConfig {

   private static final Logger log = LoggerFactory.getLogger(DaoConfig.class);

   @Autowired
   private Environment env;

   @PostConstruct
   public void init() {
      log.debug("== Application: " + env.getProperty("spring.application.name") + ", Profiles: "
            + StringUtils.join(env.getActiveProfiles(), ", ") + " ==");
      log.debug("== DB URL: " + env.getProperty("spring.datasource.signin.url"));
   }

   @Primary
   @ConfigurationProperties(prefix = "spring.datasource.signin")
   @Bean
   public DataSource dataSource() {
      return DataSourceBuilder.create().build();
   }
   
   @Bean
   public LocalSessionFactoryBean sessionFactory() {
      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
      sessionFactory.setDataSource(dataSource());
      sessionFactory.setPackagesToScan(new String[] { "com.yolo.member.promotions.entity" });
      sessionFactory.setMappingLocations(new ClassPathResource("hbm/sql-query.hbm.xml"));
      return sessionFactory;
   }

   @Bean
   @Primary
   @Autowired
   public HibernateTransactionManager transactionManager() {
      HibernateTransactionManager txManager = new HibernateTransactionManager();
      txManager.setSessionFactory(sessionFactory().getObject());
      return txManager;
   }
   
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
      return new PersistenceExceptionTranslationPostProcessor();
   }

   @Bean
   public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
      return new NamedParameterJdbcTemplate(dataSource());
   }
}
