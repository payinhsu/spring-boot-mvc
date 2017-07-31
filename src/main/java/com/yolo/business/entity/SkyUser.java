/**
 * SkyUser.java created 2017年3月2日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.business.entity;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.poseitech.dao.hibernate.AbstractModel;
import com.poseitech.dao.hibernate.type.UnixTimestampType;

/**
 * 標題：Object-Relational Mapping
 * 說明：資料庫中關聯對應物件
 * @author mango
 */
@Entity
@Table(name = "sky_user")
@TypeDefs({
   @TypeDef(name = "unixtimestamp", typeClass = UnixTimestampType.class)
})
public class SkyUser extends AbstractModel<Long> {

   /**
    * serial
    */
   private static final long serialVersionUID = -928045889742331284L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "id")
   private Long id; // 编号

   @Column(name = "name")
   private String name; // 帐号

   @Column(name = "gender")
   private String gender;  // 性别

   @Column(name = "reg_time")
   private Long regTime;   // 注册时间

   @Column(name = "reg_date")
   private Long regDate;   // 注册日期

   @Column(name = "reg_hour")
   private Long regHour;

   @Column(name = "reg_ip")
   private String regIp;   // 注册IP

   @Column(name = "reg_country")
   private Long regCountry;   // 注册时所在国家

   @Column(name = "reg_province")
   private Long regProvince;  // 注册时所在省

   @Column(name = "reg_city")
   private Long regCity;      // 注册时所在城市

   @Column(name = "reg_region")
   private String regRegion;

   @Column(name = "reg_source")
   private String regSource;  // 注册来源

   @Temporal(TemporalType.TIMESTAMP)
   @Type(type = "unixtimestamp")
   @Column(name = "login_time")
   private Date loginTime;    // 登录时间
   
   @Column(name = "login_date")
   private Long loginDate; // 登录日期

   @Column(name = "login_ip")
   private String loginIp; // 登录IP
   
   @Override
   public Long getId() {
      return id;
   }

   @Override
   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getGender() {
      return gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public Long getRegTime() {
      return regTime;
   }

   public void setRegTime(Long regTime) {
      this.regTime = regTime;
   }

   public Long getRegDate() {
      return regDate;
   }

   public void setRegDate(Long regDate) {
      this.regDate = regDate;
   }

   public Long getRegHour() {
      return regHour;
   }

   public void setRegHour(Long regHour) {
      this.regHour = regHour;
   }

   public String getRegIp() {
      return regIp;
   }

   public void setRegIp(String regIp) {
      this.regIp = regIp;
   }

   public Long getRegCountry() {
      return regCountry;
   }

   public void setRegCountry(Long regCountry) {
      this.regCountry = regCountry;
   }

   public Long getRegProvince() {
      return regProvince;
   }

   public void setRegProvince(Long regProvince) {
      this.regProvince = regProvince;
   }

   public Long getRegCity() {
      return regCity;
   }

   public void setRegCity(Long regCity) {
      this.regCity = regCity;
   }

   public String getRegRegion() {
      return regRegion;
   }

   public void setRegRegion(String regRegion) {
      this.regRegion = regRegion;
   }

   public String getRegSource() {
      return regSource;
   }

   public void setRegSource(String regSource) {
      this.regSource = regSource;
   }

   public Date getLoginTime() {
      return loginTime;
   }

   public void setLoginTime(Date loginTime) {
      this.loginTime = loginTime;
   }

   public Long getLoginDate() {
      return loginDate;
   }

   public void setLoginDate(Long loginDate) {
      this.loginDate = loginDate;
   }

   public String getLoginIp() {
      return loginIp;
   }

   public void setLoginIp(String loginIp) {
      this.loginIp = loginIp;
   }
}
