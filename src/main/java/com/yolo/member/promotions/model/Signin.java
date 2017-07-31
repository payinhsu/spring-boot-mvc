/**
 * Signin.java created 2017年3月1日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.model;

/**
 * 每日簽到專用－與前端交換物件
 * @author mango
 */
public class Signin {

   /**
    * 使用者代碼
    */
   private Long userId;
   /**
    * 使用者帳號
    */
   private String userAccount;
   
   /**
    * 簽到日
    */
   private String signDate;
   
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
   public String getSignDate() {
      return signDate;
   }
   public void setSignDate(String signDate) {
      this.signDate = signDate;
   }
}
