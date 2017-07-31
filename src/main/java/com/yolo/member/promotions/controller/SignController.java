package com.yolo.member.promotions.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.poseitech.exceptions.ErrorCodeResovler;
import com.poseitech.integration.message.ResponsePayload;
import com.poseitech.integration.util.JsonUtil;
import com.yolo.member.promotions.model.Signin;
import com.yolo.member.promotions.model.SigninData;
import com.yolo.member.promotions.model.SigninInfo;
import com.yolo.member.promotions.service.SigninService;
import com.yolo.member.promotions.vo.RecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@Scope("session")
public class SignController {
   private static Logger log = LoggerFactory.getLogger(SignController.class);

   @Autowired
   private SigninService signinService;

   @RequestMapping("/showAwardBoxes")
   public String showAwardBoxes(Model model,HttpServletRequest request,@RequestParam("userId") String userId,@RequestParam("userAccount") String userAccount) {
      log.debug("showAwardBoxes.......");
      Signin pSignin = new Signin();
//      pSignin.setUserId(Long.parseLong(userId));
      pSignin.setUserAccount(userAccount);
      String meminfoAsJson = signinService.signin(pSignin);
      ResponsePayload<SigninInfo> meminfoPayload = JsonUtil.readPayload(meminfoAsJson, new TypeReference<SigninInfo>() {
      });

      if (meminfoPayload != null && meminfoPayload.getData() != null && ("99SYS0000".equals(meminfoPayload.getErrorCode()) && meminfoPayload.getData().getTodayItem() != null)){
         request.getSession().setAttribute("todayItem",meminfoPayload.getData().getTodayItem());
         model.addAttribute("userId",userId);
         model.addAttribute("userAccount",userAccount);
         model.addAttribute("todayHasOpen",meminfoPayload.getData().isTodayHasOpen());
         model.addAttribute("countOpenBox",meminfoPayload.getData().getCountOpenBox());
         model.addAttribute("recordList",(List<RecordVo>)meminfoPayload.getData().getRecordList());
         model.addAttribute("fromPage","1");
         return "showAwardBoxes";
      }else {
         String errorCode = meminfoPayload.getErrorCode();
         String errorDesc = meminfoPayload.getErrorDesc();
         request.getSession().setAttribute("errorCode",errorCode);
         request.getSession().setAttribute("errorDesc",errorDesc);
         return "forward:/resultPage";
      }
   }

   @RequestMapping(value="/checkSingIn", method = RequestMethod.POST)
   public String checkSingIn(Model model,HttpServletRequest request,@RequestParam("userId") String userId,@RequestParam("userAccount") String userAccount)
         throws IOException {
      log.debug("checkSingIn.......");
      Signin pSignin = new Signin();
//      pSignin.setUserId(Long.parseLong(userId));
      pSignin.setUserAccount(userAccount);
      String isSignAsJson = signinService.isSignin(pSignin);
      ResponsePayload<String> isSignPayload = JsonUtil.readPayload(isSignAsJson, new TypeReference<String>(){});

      //已签到
      if(isSignPayload != null && isSignPayload.getData() != null && ("99SYS0000".equals(isSignPayload.getErrorCode()) && "true".equals(isSignPayload.getData()))){
         String getRecordJson = signinService.querySigninInfo(pSignin);
         ResponsePayload<SigninInfo> recordPayload = JsonUtil.readPayload(getRecordJson, new TypeReference<SigninInfo>(){});
         request.getSession().setAttribute("recordList",(List<RecordVo>)recordPayload.getData().getRecordList());
         request.getSession().setAttribute("todayHasOpen",recordPayload.getData().isTodayHasOpen());
         request.getSession().setAttribute("countOpenBox",recordPayload.getData().getCountOpenBox());
         request.getSession().setAttribute("userAccount",userAccount);
         request.getSession().setAttribute("errorCode",recordPayload.getErrorCode());
         request.getSession().setAttribute("errorDesc",recordPayload.getErrorDesc());
         model.addAttribute("fromPage","1");
         return "forward:/resultPage";
      }else if(isSignPayload != null && isSignPayload.getData() != null && ("99SYS0000".equals(isSignPayload.getErrorCode()) && "false".equals(isSignPayload.getData()))){
         return "forward:/showAwardBoxes";
      }else {
         String errorCode = isSignPayload.getErrorCode();
         String errorDesc = isSignPayload.getErrorDesc();
         model.addAttribute("errorCode",errorCode);
         model.addAttribute("errorDesc",errorDesc);
         return "recordPage";
      }
   }

   @RequestMapping(value="/updateSigninInfo", method = RequestMethod.POST)
   public String updateSigninInfo(HttpServletRequest request,@RequestParam("itemId") String itemId,@RequestParam("userId") String userId,@RequestParam("userAccount") String userAccount,@RequestParam("fromPage") String fromPage) {
      log.debug("updateSigninInfo.......");
      SigninData signinData = new SigninData();
      signinData.setItemId(Long.parseLong(itemId));
//      signinData.setUserId(Long.parseLong(userId));
      signinData.setUserAccount(userAccount);
      if("2".equals(fromPage)){
         String insertAsJson = signinService.insertSigninInfo(signinData);

         ResponsePayload<SigninInfo> resultPagePayload = JsonUtil.readPayload(insertAsJson, new TypeReference<String>(){});

         request.getSession().setAttribute("recordList",resultPagePayload.getData());
         if("99SYS0000".equals(resultPagePayload.getErrorCode())){
            Signin pSignin = new Signin();
//            pSignin.setUserId(Long.parseLong(userId));
            pSignin.setUserAccount(userAccount);
            String getRecordJson = signinService.querySigninInfo(pSignin);
            ResponsePayload<SigninInfo> recordPayload = JsonUtil.readPayload(getRecordJson, new TypeReference<SigninInfo>(){});
            request.getSession().setAttribute("recordList",(List<RecordVo>)recordPayload.getData().getRecordList());
            request.getSession().setAttribute("todayHasOpen",recordPayload.getData().isTodayHasOpen());
            request.getSession().setAttribute("countOpenBox",recordPayload.getData().getCountOpenBox());
            request.getSession().setAttribute("userAccount",userAccount);
            request.getSession().setAttribute("errorCode",recordPayload.getErrorCode());
            request.getSession().setAttribute("errorDesc",recordPayload.getErrorDesc());
         }else{
            request.getSession().setAttribute("errorCode",resultPagePayload.getErrorCode());
            request.getSession().setAttribute("errorDesc",resultPagePayload.getErrorDesc());
         }
      }else{
         request.getSession().setAttribute("errorCode", ErrorCodeResovler.ERROR_ILLEGAL_INPUT_ARGUMENTS);
         request.getSession().setAttribute("errorDesc", "请勿重复抽奖");
      }

      return "redirect:/resultPage";
   }

   @RequestMapping(value="/resultPage")
   public String toResultPage(Model model,HttpServletRequest request) {
      log.debug("resultPage.......");
      model.addAttribute("recordList",(List<RecordVo>)request.getAttribute("recordList"));
      model.addAttribute("userAccount",request.getSession().getAttribute("userAccount"));
      model.addAttribute("errorCode",request.getSession().getAttribute("errorCode"));
      model.addAttribute("errorDesc",request.getSession().getAttribute("errorDesc"));
      model.addAttribute("fromPage","3");
      return "recordPage";
   }
}
