/**
 * SigninController.java created 2017年2月24日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.poseitech.integration.message.ResponsePayload;
import com.poseitech.integration.util.JsonUtil;
import com.yolo.member.promotions.entity.DsActivty;
import com.yolo.member.promotions.entity.DsCase;
import com.yolo.member.promotions.model.Signin;
import com.yolo.member.promotions.model.SigninInfo;
import com.yolo.member.promotions.service.SigninService;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author mango
 */
@Controller
@RequestMapping("/${poseitech.api.version}")
public class SigninController {
   
   @Autowired
   private SigninService signinService;

   /**
    * 服務是否在線
    * @return boolean
    */
   @ApiOperation("服務是否在線")
   @RequestMapping(value="/isalive",
         method={RequestMethod.GET})
   @ResponseStatus(HttpStatus.OK)
   public @ResponseBody boolean isALive(){
      return true;
   }
   
   @RequestMapping(value = "/siginin", 
         produces = { "application/json" }, 
         consumes = { "application/json" }, 
         method = { RequestMethod.POST})
   @ResponseStatus(HttpStatus.OK)
   public @ResponseBody ResponsePayload<SigninInfo> siginin(@RequestBody Signin pSignin) {
      String meminfoAsJson = signinService.signin(pSignin);
      ResponsePayload<SigninInfo> responsePayload = JsonUtil.readPayload(meminfoAsJson, new TypeReference<SigninInfo>(){});
      return responsePayload;
   }
   
   @RequestMapping(value = "/case", 
         produces = { "application/json" }, 
//         consumes = { "application/json" }, 
         method = { RequestMethod.POST})
   @ResponseStatus(HttpStatus.OK)
   public @ResponseBody ResponsePayload<List<DsCase>> getCase() {
      String meminfoAsJson = signinService.getCase();
      ResponsePayload<List<DsCase>> responsePayload = JsonUtil.readPayload(meminfoAsJson, new TypeReference<List<DsCase>>(){});
      return responsePayload;
   }
   
   @RequestMapping(value = "/activtyList", 
         produces = { "application/json" }, 
//         consumes = { "application/json" }, 
         method = { RequestMethod.POST})
   @ResponseStatus(HttpStatus.OK)
   public @ResponseBody ResponsePayload<List<DsActivty>> findDsActivtyList() {
      String meminfoAsJson = signinService.findDsActivtyList(null, null, null, null, null, null);
      ResponsePayload<List<DsActivty>> responsePayload = JsonUtil.readPayload(meminfoAsJson, new TypeReference<List<DsActivty>>(){});
      return responsePayload;
   }
}
