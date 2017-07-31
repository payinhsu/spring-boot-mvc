/**
 * Activty.java created 2017年2月23日
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
@Table(name = "ds_activty")
public class DsActivty  extends AbstractModel<Long> {
   
   /**
    * serial id
    */
   private static final long serialVersionUID = 1088684321131603159L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "activty_id", unique = true, nullable = false)
   private Long activtyId;
   
   /**
    * 活動名稱
    */
   @Column(name = "name", nullable = false)
   private String name;

   /**
    * 活動開始時間
    */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "start_date", nullable = false, length = 19)
   private Date startDate;

   /**
    * 活動結束時間
    */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "end_date", nullable = false, length = 19)
   private Date endDate;

   /**
    * 活動狀態(0:關閉/1:啟用)
    */
   @Column(name = "status", nullable = false)
   private Integer status;
   
   /**
    * 活動類型(1:每日簽到活動)
    */
   @Column(name = "activty_type", nullable = false)
   private Integer activtyType;
   
   /**
    * 是否可重複參加(0:不可/1:可)
    */
   @Column(name = "is_repeat", nullable = false)
   private Integer isRepeat;
   
   /**
    * 每次循環的條件
    */
   @Column(name = "cycle_param", nullable = false)
   private Integer cycleParam;

   /**
    * 活動描述
    */
   @Column(name = "description")
   private String description;
   
//   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dsActivty")
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
      return this.getActivtyId();
   }

   /* (non-Javadoc)
    * @see com.poseitech.dao.hibernate.AbstractModel#setId(java.io.Serializable)
    */
   @Override
   public void setId(Long id) {
      this.setActivtyId(id);
   }

   public Long getActivtyId() {
      return activtyId;
   }

   public void setActivtyId(Long activtyId) {
      this.activtyId = activtyId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Date getStartDate() {
      return startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public Date getEndDate() {
      return endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public Integer getStatus() {
      return status;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public Integer getActivtyType() {
      return activtyType;
   }

   public void setActivtyType(Integer activtyType) {
      this.activtyType = activtyType;
   }

   public Integer getIsRepeat() {
      return isRepeat;
   }

   public void setIsRepeat(Integer isRepeat) {
      this.isRepeat = isRepeat;
   }

   public Integer getCycleParam() {
      return cycleParam;
   }

   public void setCycleParam(Integer cycleParam) {
      this.cycleParam = cycleParam;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
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
