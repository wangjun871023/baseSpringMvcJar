package com.macrosoft.core.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.macrosoft.common.constant.CommonConst;
import com.macrosoft.core.BaseForm;
import com.macrosoft.core.orm.Page;
/**
 * json处理类
 * @author xiao
 *
 */
public class ResponseUtils {

	/**
	 * 
	 * @param <T>
	 * @param T
	 * @return
	 */
  public static <T> Map<String, Object> sendList(List<T> T) {  
    return   sendList(T,true);
  }  
	/**
	 * {"dataList":[{"method":null,"ids":null,"pageSize":"15","pageSizeInt":15,"pageNo":"1","pageNoInt":1,"info":"操作失败!","infoSucess":"操作成功!","mid":"","mname":"","start":"1","limit":"15","sort":null,"dir":null,"success":false,"result":true,"resultInt":-1,"info_id":null,"id":200700,"title":"test1","description":"desc1","pageInfo":null,"orgStart":"1","orgLimit":"15"},{"method":null,"ids":null,"pageSize":"15","pageSizeInt":15,"pageNo":"1","pageNoInt":1,"info":"操作失败!","infoSucess":"操作成功!","mid":"","mname":"","start":"1","limit":"15","sort":null,"dir":null,"success":false,"result":true,"resultInt":-1,"info_id":null,"id":200701,"title":"test2","description":"desc2","pageInfo":null,"orgStart":"1","orgLimit":"15"},{"method":null,"ids":null,"pageSize":"15","pageSizeInt":15,"pageNo":"1","pageNoInt":1,"info":"操作失败!","infoSucess":"操作成功!","mid":"","mname":"","start":"1","limit":"15","sort":null,"dir":null,"success":false,"result":true,"resultInt":-1,"info_id":null,"id":200702,"title":"test3","description":"desc3","pageInfo":null,"orgStart":"1","orgLimit":"15"},{"method":null,"ids":null,"pageSize":"15","pageSizeInt":15,"pageNo":"1","pageNoInt":1,"info":"操作失败!","infoSucess":"操作成功!","mid":"","mname":"","start":"1","limit":"15","sort":null,"dir":null,"success":false,"result":true,"resultInt":-1,"info_id":null,"id":200703,"title":"test4","description":"desc4","pageInfo":null,"orgStart":"1","orgLimit":"15"},{"method":null,"ids":null,"pageSize":"15","pageSizeInt":15,"pageNo":"1","pageNoInt":1,"info":"操作失败!","infoSucess":"操作成功!","mid":"","mname":"","start":"1","limit":"15","sort":null,"dir":null,"success":false,"result":true,"resultInt":-1,"info_id":null,"id":200704,"title":"test5","description":"desc5","pageInfo":null,"orgStart":"1","orgLimit":"15"}],"success":true}
	 * @param <T>
	 * @param T
	 * @return
	 */
  public static <T> Map<String, Object> sendList(List<T> T,boolean success) {  
    Map<String, Object> map = getInstanceMap();  
    map.put("dataList", T);  
    //map.put("totalCount", "10");
    if(success==true){ 
      map.put("success", true);
      map.put(CommonConst.FORM_ACTION_KEY, true);
    }
    else{
      map.put("success", false);
      map.put(CommonConst.FORM_ACTION_KEY, false);
    }
    return map;  
  }  
 
  /**
	 * 输出page数据到json
	 * @param page 
	 * @return
	 */
  public static <T> Map<String, Object> sendPage(Page page) { 
  	return sendPage(page,true);
  } 
	/**
	 * 
	 * @param page
	 * @param success
	 * @return
	 */
  public static <T> Map<String, Object> sendPage(Page page,boolean success) {  
    Map<String, Object> map = getInstanceMap();  
    if(page!=null){
      map.put(CommonConst.PAGE_ROOT_KEY, page.getData()); 
      map.put(CommonConst.PAGIE_TOTAL_KEY, page.getTotalCount());
    }
    if(success==true){
      map.put("success", true);
      map.put(CommonConst.FORM_ACTION_KEY, true);
    }
    else{ 
      map.put("success", false);
      map.put(CommonConst.FORM_ACTION_KEY, false);
    } 
    return map;  
  }
  /**
   * 
   * @return
   */
	private static Map<String, Object> getInstanceMap() {
		return new HashMap<String, Object>();
	}
	 /**
	 * 输出entity/form数据到json
	 * @param form 
	 * @return
	 */
  public static <T> Map<String, Object> sendBaseForm(BaseForm form) { 
    Map<String, Object> map = getInstanceMap();  
    if(form!=null){
      map.put(CommonConst.ENTITY_ROOT_KEY, form);  
      if(form.getSuccess()==true){
        map.put("success", true);
        map.put(CommonConst.FORM_ACTION_KEY, true);
      }
      else{ 
        map.put("success", false);
        map.put(CommonConst.FORM_ACTION_KEY, false);
      } 
    }
    else{ 
      map.put("success", false);
      map.put(CommonConst.FORM_ACTION_KEY, false);
    }
    return map;  
  } 
}
