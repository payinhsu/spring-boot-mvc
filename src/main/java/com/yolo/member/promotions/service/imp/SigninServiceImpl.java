/**
 * SigninServiceImpl.java created 2017年2月24日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.service.imp;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poseitech.dao.hibernate.Paging;
import com.poseitech.exceptions.ErrorCodeResovler;
import com.poseitech.integration.commons.ResponseJsonMapper;
import com.poseitech.utils.DateUtil;
import com.yolo.business.dao.SkyUserDao;
import com.yolo.business.dao.SkyUserDepositDao;
import com.yolo.business.entity.SkyUser;
import com.yolo.business.entity.SkyUserDeposit;
import com.yolo.member.promotions.dao.DsActivtyDao;
import com.yolo.member.promotions.dao.DsCaseDao;
import com.yolo.member.promotions.dao.DsCateDao;
import com.yolo.member.promotions.dao.DsItemDao;
import com.yolo.member.promotions.dao.DsRecordDao;
import com.yolo.member.promotions.dao.JdbcTemplateDao;
import com.yolo.member.promotions.entity.DsActivty;
import com.yolo.member.promotions.entity.DsCase;
import com.yolo.member.promotions.entity.DsCate;
import com.yolo.member.promotions.entity.DsItem;
import com.yolo.member.promotions.entity.DsRecord;
import com.yolo.member.promotions.model.Signin;
import com.yolo.member.promotions.model.SigninData;
import com.yolo.member.promotions.model.SigninInfo;
import com.yolo.member.promotions.service.SigninException;
import com.yolo.member.promotions.service.SigninService;
import com.yolo.member.promotions.vo.RecordVo;

/**
 *
 * @author mango
 */
@Service
public class SigninServiceImpl implements SigninService {
   private static Logger log = LoggerFactory.getLogger(SigninServiceImpl.class);
   
   @Autowired
   private ResponseJsonMapper jsonMapper;
   
   @Autowired
   private DsActivtyDao dsActivtyDao;

   @Autowired
   private DsCaseDao dsCaseDao;
   
   @Autowired
   private DsCateDao dsCateDao;
   
   @Autowired
   private DsRecordDao dsRecordDao;
   
   @Autowired
   private DsItemDao dsItemDao;
   
   @Autowired
   private SkyUserDao skyUserDao;
   
   @Autowired
   private SkyUserDepositDao skyUserDepositDao;
   
   @Autowired
   private JdbcTemplateDao jdbcTemplateDao;
   
   /**
    * 是否簽到
    * @param pSignin
    * @return true:簽過到；false:未簽到
    */
   public String isSignin(Signin pSignin){
      String jsonStr = "";
      Object result = null;
      String errorCode = ErrorCodeResovler.ERROR_UNEXCEPTED_EXCEPTION;
      String errorDesc = "occurred unknown error";
      
      try{
         SkyUser skyUser = skyUserDao.getUserByName(pSignin.getUserAccount());
         if(skyUser == null){
            errorCode = "71SYS00002";
            errorDesc = "查無使用者帳號或帳號被凍結";
            return jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
         }
         
         //查使用者今天內是否有簽到
         result = dsRecordDao.isSigninByUser(skyUser.getId());
         errorCode = ErrorCodeResovler.OK;
         errorDesc = "Success!";
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (IllegalArgumentException e) {
         log.error("occur IllegalArgumentException", e);
         errorCode = ErrorCodeResovler.ERROR_ILLEGAL_INPUT_ARGUMENTS;
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (SigninException e) {
         log.error("occur DtGameException", e);
         errorCode = e.getErrorCode();
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (Exception e) {
         log.error("occur Exception", e);
         errorCode = ErrorCodeResovler.ERROR_UNEXCEPTED_EXCEPTION;
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      }
      return jsonStr;
   }
   
   /**
    * 用簽到筆數和循環天數來記算目前算是n次環循的第幾天
    * @param pSkyUser
    * @param pDsActivty
    * @return Long
    */
   private Long countOpenBox(SkyUser pSkyUser,DsActivty pDsActivty){
      Long result = 0L;
      Long userId = pSkyUser.getId();
      Long activtyId = pDsActivty.getActivtyId();
      //依使用者代碼和活動代碼查詢簽到筆數
      Long countRecords = dsRecordDao.countByUserIdAndActivtyId(userId,activtyId);
      if(countRecords == null || countRecords <= 0L){
         //查無此活動的簽到記錄，則回應沒開過寶箱
         return result;
      }
      Integer cycleParam = pDsActivty.getCycleParam();
      if(cycleParam == null || cycleParam <= 0){
         //若無循環天數，則簽到筆數等同開寶次數
         result = countRecords;
         return result;
      }
      
      Long cycleDay = Long.valueOf(cycleParam);
      //用簽到筆數和循環天數來記算目前算是n次環循的第幾天
      Long val = (countRecords%cycleDay);
      if(val == 0L){
         //若餘數剛好為0，則用循環天數
         result = cycleDay;
      }else{
         //反之，使用餘數
         result = val;
      }
      return result;
   }
   
   /**
    * 查詢最新簽到抽到的寶物
    * @param pUserId
    * @return DsItem
    */
   private DsItem getTodayItem(Long pUserId){
      DsItem result = null;
      DsRecord record = dsRecordDao.getNewSigninByUser(pUserId);
      if(record != null && record.getItemId() != null){
         result = dsItemDao.get(record.getItemId());
      }
      return result;
   }
   
   //假的連續儲值
   private int countDepositDays(SkyUser skyUser,DsActivty activty){
      Integer userId = Integer.valueOf(String.valueOf(skyUser.getId()));
      List<SkyUserDeposit> list = skyUserDepositDao.findUserDepositByActivty(userId, activty.getStartDate(), activty.getEndDate(), activty.getCycleParam());
      Map<String,Object> depositMap = new HashMap<String,Object>();
      for(SkyUserDeposit deposit:list){
         Date crtTime = deposit.getCreateTime();
         String DateStr = DateUtil.format(crtTime);
         depositMap.put(DateStr, deposit);
      }
      
      
      //TODO 
      //2.2.查詢使用者儲值記錄，依activty.cycleParam 每次循環的條件，ex:15天內
      //2.3.邏輯判斷是否為連續儲值
      return (int)(Math.random()*15);
   }
   
   /**
    * 簽到取獎項
    * @return DsItem
    */
   private DsCase filterCase(){
      //TODO
      //3.1連續儲值的查詢結果查詢ds_case，
      //3.2比對滿足哪裡一個ds_case.condition
      //3.3.依ds_activty.id 和 ds_case.id查詢ds_cate
      //4.takeRandom依機率挑選ds_cate淸單中的一筆
      //5.依挑選結果查詢ds_item淸單
      //6.takeRandom依機率挑選ds_item淸單中的一筆
      DsCase result = null;
      //連續存儲天數
      int countDepositDays = (int)(Math.random()*15);
      log.debug("countDepositDays = {}",countDepositDays);
      
      List<DsCase> list = dsCaseDao.getAll();
      for(DsCase dsCase : list){
         String condi = dsCase.getCondition();
         String[] ss = condi.split(",");
         List<String> condiList = Arrays.asList(ss);
         if(condiList.contains(String.valueOf(countDepositDays))){
            result = dsCase;
            break;
         }
      }
      return result;
   }
   
   /**
    * 查詢簽到結果
    * @param pSignin
    * @return SigninInfo
    */
   public String querySigninInfo(Signin pSignin){
      String jsonStr = "";
      Object result = null;
      String errorCode = ErrorCodeResovler.ERROR_UNEXCEPTED_EXCEPTION;
      String errorDesc = "occurred unknown error";
      
      try{
         SkyUser skyUser = skyUserDao.getUserByName(pSignin.getUserAccount());
         if(skyUser == null){
            errorCode = "71SYS00002";
            errorDesc = "查無使用者帳號或帳號被凍結";
            return jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
         }
         
         //查詢目前有效的簽到活動
         DsActivty activty = dsActivtyDao.getVaildSigninActivty();
         if(activty == null){
            errorCode = "71SYS00001";
            errorDesc = "查無有效的簽到活動或活動過期";
            return jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
         }
         
         //淸單給十五筆
         int limit = 15;
         List<RecordVo> recordList = jdbcTemplateDao.findNewVoList(limit);
         //今日是否有簽到
         boolean todayHasOpen = dsRecordDao.isSigninByUser(skyUser.getId());
         //用簽到筆數和循環天數來記算目前算是n次環循的第幾天
         Long countOpenBox = this.countOpenBox(skyUser, activty);
         //查詢最新簽到抽到的寶物
         DsItem todayItem = this.getTodayItem(skyUser.getId());
         
         SigninInfo signinInfo = new SigninInfo();
         signinInfo.setUserId(skyUser.getId());
         signinInfo.setUserAccount(skyUser.getName());
         signinInfo.setTodayHasOpen(todayHasOpen);
         signinInfo.setCountOpenBox(countOpenBox);
         if(recordList != null && recordList.size() > 0){
            signinInfo.setRecordList(recordList);
         }
         if(todayItem != null){
            signinInfo.setTodayItem(todayItem);
         }
         
         result = signinInfo;
         errorCode = ErrorCodeResovler.OK;
         errorDesc = "Success!";
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
         
      } catch (IllegalArgumentException e) {
         log.error("occur IllegalArgumentException", e);
         errorCode = ErrorCodeResovler.ERROR_ILLEGAL_INPUT_ARGUMENTS;
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (SigninException e) {
         log.error("occur DtGameException", e);
         errorCode = e.getErrorCode();
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (Exception e) {
         log.error("occur Exception", e);
         errorCode = ErrorCodeResovler.ERROR_UNEXCEPTED_EXCEPTION;
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      }
      return jsonStr;
   }
   
   /**
    * 簽到
    * @param pSignin
    * @return Stirng
    */
   public String signin(Signin pSignin){
      String jsonStr = "";
      Object result = null;
      String errorCode = ErrorCodeResovler.ERROR_UNEXCEPTED_EXCEPTION;
      String errorDesc = "occurred unknown error";
      
      try{
         //2.1查詢使用者
         SkyUser skyUser = skyUserDao.getUserByName(pSignin.getUserAccount());
         if(skyUser == null){
            errorCode = "71SYS00002";
            errorDesc = "查無使用者帳號或帳號被凍結";
            return jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
         }
         
         //查詢目前有效的簽到活動
         DsActivty activty = dsActivtyDao.getVaildSigninActivty();
         if(activty == null){
            errorCode = "71SYS00001";
            errorDesc = "查無有效的簽到活動或活動過期";
            return jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
         }
         
         //淸單給五筆
         int limit = 5;
         List<RecordVo> recordList = jdbcTemplateDao.findNewVoList(limit);
         //今日是否有簽到
         boolean todayHasOpen = dsRecordDao.isSigninByUser(skyUser.getId());
         //用簽到筆數和循環天數來記算目前算是n次環循的第幾天
         Long countOpenBox = this.countOpenBox(skyUser, activty);
         
         
         
         //取出使用者目前落在哪個情境
         DsCase filterCase = this.filterCase();
         Long takeRandomCaseId = filterCase.getCaseId();
         //查詢最新簽到抽到的寶物
         log.debug("takeRandomCaseId__ = {}",takeRandomCaseId);
         List<DsCate> dsCateList = dsCateDao.findDsCateByCaseId(takeRandomCaseId,activty.getActivtyId());
         //今日中獎的分類
         DsCate toDayCate = this.takeDsCateRandom(dsCateList);
         Long toDayCateId = toDayCate.getCateId();
         List<DsItem> dsItemList = dsItemDao.findItemByCateId(toDayCateId);
         DsItem todayItem = this.takeDsItemRandom(dsItemList);
         
         SigninInfo signinInfo = new SigninInfo();
         signinInfo.setUserId(skyUser.getId());
         signinInfo.setUserAccount(skyUser.getName());
         signinInfo.setTodayHasOpen(todayHasOpen);
         signinInfo.setCountOpenBox(countOpenBox);
         if(recordList != null && recordList.size() > 0){
            signinInfo.setRecordList(recordList);
         }
         if(todayItem != null){
            signinInfo.setTodayItem(todayItem);
         }
         
         result = signinInfo;
         errorCode = ErrorCodeResovler.OK;
         errorDesc = "Success!";
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
         
      } catch (IllegalArgumentException e) {
         log.error("occur IllegalArgumentException", e);
         errorCode = ErrorCodeResovler.ERROR_ILLEGAL_INPUT_ARGUMENTS;
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (SigninException e) {
         log.error("occur DtGameException", e);
         errorCode = e.getErrorCode();
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (Exception e) {
         log.error("occur Exception", e);
         errorCode = ErrorCodeResovler.ERROR_UNEXCEPTED_EXCEPTION;
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      }
      return jsonStr;
   }
   
   /**
    * 將簽到結果寫入資料庫
    * @param pSigninData
    * @return String
    * @see com.yolo.member.promotions.service.SigninService#insertSigninInfo(com.yolo.member.promotions.model.SigninData)
    */
   @Transactional
   public String insertSigninInfo(SigninData pSigninData){
      String jsonStr = "";
      Object result = null;
      String errorCode = ErrorCodeResovler.ERROR_UNEXCEPTED_EXCEPTION;
      String errorDesc = "occurred unknown error";
      Date sysDate = new Date();
      try{
         if(pSigninData == null){
            throw new IllegalArgumentException("pSigninData msut not empty");
         }
         
         SkyUser skyUser = skyUserDao.getUserByName(pSigninData.getUserAccount());
         if(skyUser == null){
            errorCode = "71SYS00002";
            errorDesc = "查無使用者帳號或帳號被凍結";
            return jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
         }
         Long pItemId = pSigninData.getItemId();
         if(pItemId == null){
            throw new IllegalArgumentException("pItemId msut not empty");
         }
         
         DsItem dsItem = dsItemDao.get(pItemId);
         if(dsItem == null){
            errorCode = "71SYS00003";
            errorDesc = "查無此獎項";
            return jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
         }
         
         DsCate dsCate = dsCateDao.get(dsItem.getCateId());
         if(dsCate == null){
            errorCode = "71SYS00004";
            errorDesc = "查無此獎項分類";
            return jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
         }
         
         DsCase dsCase = dsCaseDao.get(dsCate.getCaseId());
         DsActivty dsActivty = dsActivtyDao.get(dsCate.getActivtyId());
         if(dsActivty == null){
            errorCode = "71SYS00005";
            errorDesc = "查無此獎項分類情境";
            return jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
         }
         
         //7.insert into ds_record
         DsRecord entity = new DsRecord();
         entity.setUserId(skyUser.getId());
         entity.setUserName(skyUser.getName());
         entity.setItemId(dsItem.getItemId());
         entity.setCateId(dsCate.getCateId());
         entity.setCaseId(dsCase.getCaseId());
         entity.setActivtyId(dsActivty.getActivtyId());
         entity.setSignDate(sysDate);
         
         entity.setCreateUser("sys");
         entity.setCreateDate(sysDate);
         entity.setModifyUser("sys");
         entity.setModifyDate(sysDate);
         Long insertPk = dsRecordDao.save(entity);
         result = insertPk;
         errorCode = ErrorCodeResovler.OK;
         errorDesc = "Success!";
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
         
      } catch (IllegalArgumentException e) {
         log.error("occur IllegalArgumentException", e);
         errorCode = ErrorCodeResovler.ERROR_ILLEGAL_INPUT_ARGUMENTS;
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (SigninException e) {
         log.error("occur DtGameException", e);
         errorCode = e.getErrorCode();
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (Exception e) {
         log.error("occur Exception", e);
         errorCode = ErrorCodeResovler.ERROR_UNEXCEPTED_EXCEPTION;
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      }
      return jsonStr;
   }
   
   @Transactional
   public String getCase(){
      String jsonStr = "";
      Object result = null;
      String errorCode = ErrorCodeResovler.ERROR_UNEXCEPTED_EXCEPTION;
      String errorDesc = "occurred unknown error";
      
      try{
         List<DsCase> list = dsCaseDao.getAll();
         if(list != null && list.size() > 0){
            result = list;
         }
         errorCode = ErrorCodeResovler.OK;
         errorDesc = "Success!";
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (IllegalArgumentException e) {
         log.error("occur IllegalArgumentException", e);
         errorCode = ErrorCodeResovler.ERROR_ILLEGAL_INPUT_ARGUMENTS;
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (SigninException e) {
         log.error("occur DtGameException", e);
         errorCode = e.getErrorCode();
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (Exception e) {
         log.error("occur Exception", e);
         errorCode = ErrorCodeResovler.ERROR_UNEXCEPTED_EXCEPTION;
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      }
      return jsonStr;
   }
   
   public String findDsActivtyList(String pName,Date pStartDate,Date pEndDate,Integer pIsRepeat,Integer pStatus,Paging pPaging){
      String jsonStr = "";
      Object result = null;
      String errorCode = ErrorCodeResovler.ERROR_UNEXCEPTED_EXCEPTION;
      String errorDesc = "occurred unknown error";
      
      try{
         List<DsActivty> list = dsActivtyDao.findDsActivtyList(pName, pStartDate, pEndDate, pIsRepeat, pStatus, pPaging);
         if(list != null && list.size() > 0){
            result = list;
         }
         errorCode = ErrorCodeResovler.OK;
         errorDesc = "Success!";
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (IllegalArgumentException e) {
         log.error("occur IllegalArgumentException", e);
         errorCode = ErrorCodeResovler.ERROR_ILLEGAL_INPUT_ARGUMENTS;
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (SigninException e) {
         log.error("occur DtGameException", e);
         errorCode = e.getErrorCode();
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      } catch (Exception e) {
         log.error("occur Exception", e);
         errorCode = ErrorCodeResovler.ERROR_UNEXCEPTED_EXCEPTION;
         errorDesc = e.getMessage();
         jsonStr = jsonMapper.toJson(result, errorCode, errorDesc);
      }
      return jsonStr;
   }
   
   /**
    * 依淸單中各自己機率抽出一個
    * @param dsCateList
    * @return DsCate
    */
   private DsCate takeDsCateRandom(List<DsCate> dsCateList){
      DsCate result = null;
      double p = Math.random();
      double cumulativeProbability = 0.0;
      for(DsCate dsCate : dsCateList){
         double val = (dsCate.getPercent()/100d);
         cumulativeProbability += val;
         if (p <= cumulativeProbability){
            return dsCate;
         }
      }
      return result;
   }
   
   /**
    * 依淸單中各自己機率抽出一個
    * @param dsItemList
    * @return DsItem
    */
   private DsItem takeDsItemRandom(List<DsItem> dsItemList){
      DsItem result = null;
      double p = Math.random();
      double cumulativeProbability = 0.0;
      for(DsItem dsItem : dsItemList){
         double val = (dsItem.getPercent()/100d);
         cumulativeProbability += val;
         if (p <= cumulativeProbability){
            return dsItem;
         }
      }
      return result;
   }
}
