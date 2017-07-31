/**
 * SigninData.java created 2017年3月3日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.model;

/**
 *
 * @author mango
 */
public class SigninData {
   private Long userId;
   private String userAccount;
   private Long itemId;
   public Long getUserId() {
      return userId;
   }
   public void setUserId(Long userId) {
      this.userId = userId;
   }
   public String getUserAccount() {
      return userAccount;
   }
   public void setUserAccount(String userAccount) {
      this.userAccount = userAccount;
   }
   public Long getItemId() {
      return itemId;
   }
   public void setItemId(Long itemId) {
      this.itemId = itemId;
   }
}
