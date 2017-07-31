/**
 * Record.java created 2017年2月23日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.poseitech.dao.hibernate.AbstractModel;

/**
 * 標題：Object-Relational Mapping
 * 說明：資料庫中關聯對應物件
 * @author mango
 */
@Entity
@Table(name = "ds_record")
public class DsRecord extends AbstractModel<Long> {
   
   /**
    * serial id
    */
   private static final long serialVersionUID = 3564107758395479460L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "record_id", unique = true, nullable = false)
   private Long recordId;
   
   @Column(name = "user_id", nullable = false)
   private Long userId;
   
   @Column(name = "user_name", nullable = false)
   private String userName;
   
   @Column(name = "sign_date", nullable = false)
   private Date signDate;
   
   @Column(name = "activty_id", nullable = false)
   private Long activtyId;
   
   @Column(name = "case_id", nullable = false)
   private Long caseId;
   
   @Column(name = "cate_id", nullable = false)
   private Long cateId;
   
   @Column(name = "item_id", nullable = false)
   private Long itemId;
   
   @Column(name = "create_user")
   private String createUser;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "create_date", length = 19)
   private Date createDate;

   @Column(name = "modify_user")
   private String modifyUser;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "modify_date", length = 19)
   private Date modifyDate;

   /* (non-Javadoc)
    * @see com.poseitech.dao.hibernate.AbstractModel#getId()
    */
   @Override
   public Long getId() {
      return this.getRecordId();
   }

   /* (non-Javadoc)
    * @see com.poseitech.dao.hibernate.AbstractModel#setId(java.io.Serializable)
    */
   @Override
   public void setId(Long id) {
      this.setRecordId(id);
   }

   public Long getRecordId() {
      return recordId;
   }

   public void setRecordId(Long recordId) {
      this.recordId = recordId;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public Date getSignDate() {
      return signDate;
   }

   public void setSignDate(Date signDate) {
      this.signDate = signDate;
   }

   public Long getActivtyId() {
      return activtyId;
   }

   public void setActivtyId(Long activtyId) {
      this.activtyId = activtyId;
   }

   public Long getCaseId() {
      return caseId;
   }

   public void setCaseId(Long caseId) {
      this.caseId = caseId;
   }

   public Long getCateId() {
      return cateId;
   }

   public void setCateId(Long cateId) {
      this.cateId = cateId;
   }

   public Long getItemId() {
      return itemId;
   }

   public void setItemId(Long itemId) {
      this.itemId = itemId;
   }

   public String getCreateUser() {
      return createUser;
   }

   public void setCreateUser(String createUser) {
      this.createUser = createUser;
   }

   public Date getCreateDate() {
      return createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   public String getModifyUser() {
      return modifyUser;
   }

   public void setModifyUser(String modifyUser) {
      this.modifyUser = modifyUser;
   }

   public Date getModifyDate() {
      return modifyDate;
   }

   public void setModifyDate(Date modifyDate) {
      this.modifyDate = modifyDate;
   }
}
