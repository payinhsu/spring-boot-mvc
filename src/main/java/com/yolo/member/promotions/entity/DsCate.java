/**
 * Cate.java created 2017年2月23日
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
@Table(name = "ds_cate")
public class DsCate extends AbstractModel<Long> {
   
   /**
    * serial id
    */
   private static final long serialVersionUID = 1385453636131108248L;
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "cate_id", unique = true, nullable = false)
   private Long cateId;
   
   @Column(name = "name", nullable = false)
   private String name;
   
   @Column(name = "percent", nullable = false)
   private Integer percent;
   
//   @ManyToOne(fetch = FetchType.LAZY)
//   @JoinColumn(name = "activty_id", nullable = false)
//   private DsActivty dsActivty;
   
//   @ManyToOne(fetch = FetchType.LAZY)
//   @JoinColumn(name = "case_id", nullable = false)
//   private DsCase dsCase;
   
   @Column(name = "activty_id", nullable = false)
   private Long activtyId;
   
   @Column(name = "case_id", nullable = false)
   private Long caseId;
   
//   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dsCate")
//   private Set<DsItem> dsItems = new HashSet<DsItem>(0);
   
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
      return this.getCateId();
   }

   /* (non-Javadoc)
    * @see com.poseitech.dao.hibernate.AbstractModel#setId(java.io.Serializable)
    */
   @Override
   public void setId(Long id) {
      this.setCateId(id);
   }

   public Long getCateId() {
      return cateId;
   }

   public void setCateId(Long cateId) {
      this.cateId = cateId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getPercent() {
      return percent;
   }

   public void setPercent(Integer percent) {
      this.percent = percent;
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
