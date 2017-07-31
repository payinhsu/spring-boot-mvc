package com.yolo.member.promotions.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

   private static Logger log = LoggerFactory.getLogger(DemoController.class);
   
   @RequestMapping("/")
   public String demo() {
      log.debug("DEMO.......");
      return "demoTakeRandom";
   }

}

