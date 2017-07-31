/**
 * DtGameException.java created 2016年12月28日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.service;

import com.poseitech.exceptions.RootException;
import com.poseitech.lang.Assert;

/**
 * @author Mango
 */
public class SigninException extends RootException {
   
   /**
    * 序號
    */
   private static final long serialVersionUID = 4736808097835219611L;
   final private String apiUrl;

   /**
    * @param pErrorCode
    * @param pMessage
    */
   public SigninException(String pErrorCode, String pMessage, String aApiUrl) {
      super(pErrorCode, pMessage);
      
      Assert.notNull(aApiUrl, "DtGame reqeust api url must not be null.");
      
      this.apiUrl = aApiUrl;
      appendMessage("DtGame api url: " + this.apiUrl);
   }

   /**
    * @param pErrorCode
    * @param pMessage
    * @param pCause
    */
   public SigninException(String pErrorCode, String pMessage, String aApiUrl, Throwable pCause) {
      super(pErrorCode, pMessage, pCause);
      
      Assert.notNull(aApiUrl, "DtGame reqeust api url must not be null.");
      
      this.apiUrl = aApiUrl;
      appendMessage("DtGame api url: " + this.apiUrl);
   }
}
