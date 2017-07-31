/**
 * Case.java created 2017年2月23日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "ds_case")
public class DsCase extends AbstractModel<Long> {
   
   /**
    * serial id
    */
   private static final long serialVersionUID = -5119630867851089129L;
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "case_id", unique = true, nullable = false)
   private Long caseId;
   
   @Column(name = "condition", nullable = false)
   private String condition;
   
   @Column(name = "name", nullable = false)
   private String name;
   
   @Column(name = "desc")
   private String desc;
   
//   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dsCase")
//   private Set<DsCate> dsCates = new HashSet<DsCate>(0);
   
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
      return this.getCaseId();
   }

   /* (non-Javadoc)
    * @see com.poseitech.dao.hibernate.AbstractModel#setId(java.io.Serializable)
    */
   @Override
   public void setId(Long id) {
      this.setCaseId(id);
   }

   public Long getCaseId() {
      return caseId;
   }

   public void setCaseId(Long caseId) {
      this.caseId = caseId;
   }

   public String getCondition() {
      return condition;
   }

   public void setCondition(String condition) {
      this.condition = condition;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDesc() {
      return desc;
   }

   public void setDesc(String desc) {
      this.desc = desc;
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
