/**
 * Item.java created 2017年2月23日
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
@Table(name = "ds_item")
public class DsItem extends AbstractModel<Long> {
   
   /**
    * serial id
    */
   private static final long serialVersionUID = -8484370577918898392L;
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "item_id", unique = true, nullable = false)
   private Long itemId;
   
   @Column(name = "name", nullable = false)
   private String name;
   
   @Column(name = "type", nullable = false)
   private Integer type;
   
   @Column(name = "value", nullable = false)
   private Double value;
   
//   @ManyToOne(fetch = FetchType.LAZY)
//   @JoinColumn(name = "cate_id", nullable = false)
//   private DsCate dsCate;
   
   @Column(name = "cate_id", nullable = false)
   private Long cateId;
   
   @Column(name = "percent", nullable = false)
   private Integer percent;
   
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
      return this.getItemId();
   }

   /* (non-Javadoc)
    * @see com.poseitech.dao.hibernate.AbstractModel#setId(java.io.Serializable)
    */
   @Override
   public void setId(Long id) {
      this.setItemId(id);
   }

   public Long getItemId() {
      return itemId;
   }

   public void setItemId(Long itemId) {
      this.itemId = itemId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getType() {
      return type;
   }

   public void setType(Integer type) {
      this.type = type;
   }

   public Double getValue() {
      return value;
   }

   public void setValue(Double value) {
      this.value = value;
   }

   public Long getCateId() {
      return cateId;
   }

   public void setCateId(Long cateId) {
      this.cateId = cateId;
   }

   public Integer getPercent() {
      return percent;
   }

   public void setPercent(Integer percent) {
      this.percent = percent;
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
