/**
 * JdbcTemplateDao.java created 2017年3月3日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.yolo.member.promotions.dao;

import java.util.List;

import com.yolo.member.promotions.vo.RecordVo;

/**
 *
 * @author mango
 */
public interface JdbcTemplateDao {

   /**
    * 查詢中獎淸單
    * @param pLimit
    * @return  List<RecordVo>
    */
   public List<RecordVo> findNewVoList(Integer pLimit);
}
