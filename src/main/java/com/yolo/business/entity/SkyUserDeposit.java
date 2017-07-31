/**
 * SkyUserDeposit.java created 2017年3月3日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.business.entity;

import java.util.Date;

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
@Table(name = "sky_user_deposit")
@TypeDefs({
   @TypeDef(name = "unixtimestamp", typeClass = UnixTimestampType.class)
})
public class SkyUserDeposit extends AbstractModel<Long> {
   
   /**
    * serial
    */
   private static final long serialVersionUID = -1106470659212618850L;
   
//   CREATE TABLE sky_user_deposit
//   (
//      id               bigint(20),
//      sn               varchar(32) DEFAULT '',
//      pay_sn           varchar(32) DEFAULT '',
//      user_id          int(11) DEFAULT 0,
//      money            double(10, 2) DEFAULT 0.00,
//      fee              double(10, 2) DEFAULT 0.00,
//      bank_name        varchar(32) DEFAULT '',
//      bank_user        varchar(32) DEFAULT '',
//      bank_account     varchar(32) DEFAULT '',
//      payment_id       int(11) DEFAULT 0,
//      deposit_mode     varchar(64) DEFAULT '0',
//      payee            varchar(64) DEFAULT '',
//      remark           varchar(500) DEFAULT '',
//      create_time      bigint(15) UNSIGNED DEFAULT 0,
//      create_date      bigint(15) UNSIGNED DEFAULT 0,
//      create_hour      int(11) DEFAULT 0,
//      dispose_time     bigint(15) UNSIGNED DEFAULT 0,
//      dispose_date     bigint(15) UNSIGNED DEFAULT 0,
//      dispose_remark   varchar(500) DEFAULT '',
//      dispose_user     int(11) DEFAULT 0,
//      status           smallint(2) DEFAULT 0,
//      pay_time         bigint(15) UNSIGNED DEFAULT 0,
//      pay_date         bigint(15) UNSIGNED DEFAULT 0,
//      ip               varchar(30) DEFAULT '',
//      country          int(11) DEFAULT 0,
//      province         int(11) DEFAULT 0,
//      payid            varchar(200)
//   )
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id; // 编号
   
   @Column(name = "sn")
   private String sn;
   
   @Column(name = "pay_sn")
   private String paySn;
   
   @Column(name = "user_id")
   private Integer userId;
   
   @Column(name = "status")
   private Integer status;
   
   @Temporal(TemporalType.TIMESTAMP)
   @Type(type = "unixtimestamp")
   @Column(name = "create_time")
   private Date createTime;

   /* (non-Javadoc)
    * @see com.poseitech.dao.hibernate.AbstractModel#getId()
    */
   @Override
   public Long getId() {
      return id;
   }

   /* (non-Javadoc)
    * @see com.poseitech.dao.hibernate.AbstractModel#setId(java.io.Serializable)
    */
   @Override
   public void setId(Long id) {
      this.id = id;
   }

   public String getSn() {
      return sn;
   }

   public void setSn(String sn) {
      this.sn = sn;
   }

   public String getPaySn() {
      return paySn;
   }

   public void setPaySn(String paySn) {
      this.paySn = paySn;
   }

   public Integer getUserId() {
      return userId;
   }

   public void setUserId(Integer userId) {
      this.userId = userId;
   }

   public Integer getStatus() {
      return status;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public Date getCreateTime() {
      return createTime;
   }

   public void setCreateTime(Date createTime) {
      this.createTime = createTime;
   }
}
