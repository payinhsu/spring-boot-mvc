/**
 * Signin.java created 2017年2月24日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.service;

import java.util.Date;

import com.poseitech.dao.hibernate.Paging;
import com.yolo.member.promotions.model.Signin;
import com.yolo.member.promotions.model.SigninData;

/**
 *
 * @author mango
 */
public interface SigninService {
   
   /**
    * 是否簽到
    * @param pSignin
    * @return true:簽過到；false:未簽到
    */
   public String isSignin(Signin pSignin);
   
   /**
    * 查詢簽到結果
    * @param pSignin
    * @return SigninInfo
    */
   public String querySigninInfo(Signin pSignin);
   
   /**
    * 簽到
    * @param pSignin
    * @return Stirng
    */
   public String signin(Signin pSignin);
   
   /**
    * 將簽到結果寫入資料庫
    * @param pSigninData
    * @return String
    */
   public String insertSigninInfo(SigninData pSigninData);

   /**
    * 取得所有觸發的事件
    * @return String
    */
   public String getCase();
   
   /**
    * 依條件查詢活動資訊淸單
    * @param pName
    * @param pStartDate
    * @param pEndDate
    * @param pIsRepeat
    * @param pStatus
    * @param pPaging
    * @return String
    */
   public String findDsActivtyList(String pName,Date pStartDate,Date pEndDate,Integer pIsRepeat,Integer pStatus,Paging pPaging);
}
