/**
 * SigninInfo.java created 2017年3月1日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.model;

import java.util.List;

import com.yolo.member.promotions.entity.DsItem;
import com.yolo.member.promotions.vo.RecordVo;

/**
 * 簽到專用－簽到資訊
 * @author mango
 */
public class SigninInfo {
   private Long userId;
   private String userAccount;
   private boolean todayHasOpen;
   private Long countOpenBox;
   private DsItem todayItem;
   private List<RecordVo> recordList;
   
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
   public boolean isTodayHasOpen() {
      return todayHasOpen;
   }
   public void setTodayHasOpen(boolean todayHasOpen) {
      this.todayHasOpen = todayHasOpen;
   }
   public Long getCountOpenBox() {
      return countOpenBox;
   }
   public void setCountOpenBox(Long countOpenBox) {
      this.countOpenBox = countOpenBox;
   }
   public List<RecordVo> getRecordList() {
      return recordList;
   }
   public void setRecordList(List<RecordVo> recordList) {
      this.recordList = recordList;
   }
   public DsItem getTodayItem() {
      return todayItem;
   }
   public void setTodayItem(DsItem todayItem) {
      this.todayItem = todayItem;
   }
}
