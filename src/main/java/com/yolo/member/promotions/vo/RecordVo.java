/**
 * RecordVo.java created 2017年3月3日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.vo;

import java.util.Date;

import com.poseitech.dao.hibernate.AbstractModel;

/**
 *
 * @author mango
 */
public class RecordVo extends AbstractModel<Long> {
   
   /**
    * serial
    */
   private static final long serialVersionUID = 112263194251518625L;
   
   private Long recordId;

   private Long userId;
   
   private String userName;
   
   private Date signDate;
   
   private String signDateStr;

   private Long itemId;
   
   private String itemName;

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

   public String getSignDateStr() {
      return signDateStr;
   }

   public void setSignDateStr(String signDateStr) {
      this.signDateStr = signDateStr;
   }

   public Long getItemId() {
      return itemId;
   }

   public void setItemId(Long itemId) {
      this.itemId = itemId;
   }

   public String getItemName() {
      return itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

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
}
