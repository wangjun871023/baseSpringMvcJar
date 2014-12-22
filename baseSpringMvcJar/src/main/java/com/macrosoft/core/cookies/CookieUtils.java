package com.macrosoft.core.cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.macrosoft.common.constant.CommonConst;
import com.macrosoft.common.md5.MD5;
import com.macrosoft.common.string.StringUtils;

/**
 * sso 
 * @author Administrator
 *
 */ 
public final class CookieUtils {    
      private Log logger=LogFactory.getLog(CookieUtils.class);  
      /**
       * 
       */
	   private static CookieUtils instance=new CookieUtils(); 
	    /**
	     * 
	     */
		private CookieUtils(){ 
			//getSecretKey();
		}
		/**
		 * 
		 * @return
		 */
		public static CookieUtils getInstance(){
			return instance;
		}
    /**
     * 写 cookie的方法 
     * @param request
     * @param response
     * @param session
     */
	public void writeCookie(HttpServletRequest request,HttpServletResponse response
			,UserSession userSession) {
		if(request!=null){
			//得到cookie信息
			Cookie[] cookies=request.getCookies(); 
		    /*    
		    response.setHeader("Pragma", "no-cache"); 
		    response.setHeader("Cache-Control", "no-cache"); 
		    response.setDateHeader("Expires", 0);
		      */  
			if (cookies!=null&& cookies.length != 0) { 
				for (int i=0,len=cookies.length;i<len; i++) { 
					Cookie c=cookies[i]; 
					Cookie cookie =null;
					//加密cookie
                    String cookievalue=encryCookie(userSession);//编码 
					 //读取名字类似与unitid_1的cookie，后面的数字的记录的id，如果有取出来，在追加信息。
	                if (c.getName().equals(CommonConst.SYS_COOKIE_NAME)==true) { 
                         //创建cookie对象
	                     cookie=new Cookie(CommonConst.SYS_COOKIE_NAME,cookievalue);  //编码后添加都cookie 	                  
	                } else {//如果没有 直接存储 
	                  cookie=new Cookie(CommonConst.SYS_COOKIE_NAME,cookievalue); 	                    
	                }  
	                //设置最大时效，默认该cookie是存储在浏览器的内在中，用户关闭浏览器则被删除，下面的方法是将cookie存储在硬盘上。

                    //c.setMaxAge(60*60*24);一天，如果设置为0则是删除该cookie
	                cookie.setMaxAge(60*60); 
	                //设置cookie保存路径
	                //设置或取得cookie适用的路径cookie.setPath(“/”);指定服务器的所有页面都应该收到该cookie
                    cookie.setPath(CommonConst.SYS_COOKIE_PATH); 
                    //设置或读取该cookie适用的域
                    //setSource()/getSource();指定cookie是否只能通过加密连接（SSL）  默认false，表示cookie适用所有连接
                    //cookie.setDomain(pattern);
                    //将cookie放入到HTTP响应报头，可以使用HttpServletResponse的addCookie方法，此方法不修改之前指定的Set-Cookie报头，而是创建新的报头。
                    response.addCookie(cookie); 
	            } 
	        } 
		} 
	  } 
    /**
     * 删除cookie
     * @param request
     * @param response
     */
	public void deleteCookie(HttpServletRequest request,HttpServletResponse response){
		if(request!=null){
			Cookie[] cookies=request.getCookies();   
			if(cookies!=null){
				for (int i=0; i < cookies.length; i++) {  
			  	  	Cookie cookie=(Cookie)cookies[i]; 
			  	  if (CommonConst.SYS_COOKIE_NAME.equals(cookies[i].getName())==true) {  
			           try {   
			               cookie=cookies[i];//new Cookie(cookieName, null);  
			               cookie.setMaxAge(0); 
			               cookie.setValue("");
			               cookie.setPath(CommonConst.SYS_COOKIE_PATH);  
			               response.addCookie(cookie);  
			               // session.remove("userName");  
			           } catch (Exception ex) {  
			               logger.error(ex,ex);
			           }  
			       }  
			     }  
			 }
		} 
	} 
	
	/**
	 * 得到cookie的数据
	 * @param request
	 * @param cName
	 * @return
	 */
	public  String getValue(HttpServletRequest request,String cName){ 
        Cookie[] cookies=request.getCookies(); 
        String result=null;
        if(cookies != null){ 
           for (int i=0,len=cookies.length;i<len;i++) { 
               Cookie cookie=cookies[i]; 
               if (cName.equals(cookie.getName())) { 
                  result=cookie.getValue(); 
                  break;
               } 
           } 
       } 
        return result; 
    }
	 /**
	  * 得到cookie信息
	  * @param request
	  * @param cName
	  * @return
	  */
	 private  Cookie getCookie(HttpServletRequest request,String cName){ 
		    Cookie result=null;
	        Cookie[] cookies=request.getCookies(); 
		    if (cookies==null||cName==null||cName.length()==0) {
		        return result;
		      }
	        if (cookies==null||cName==null||cName.length()==0) {
		        return result;
		      }
	        if(cookies != null){ 
	           for (int i=0,len=cookies.length;i<len; i++) { 
	               Cookie cookie=cookies[i]; 
	               if (cName.equals(cookie.getName())==true) {
	            	   //if (name.equals(cookies[i].getName())&& request.getServerName().equals(cookies[i].getDomain())) {
	            	   result=cookie; 
	                   break;
	               } 
	           } 
	       } 
	        return result; 
	 }
	 /**
	  * 得到用户session信息
	  * @param request 
	  * @return
	  */
	 public  UserSession getUserSession(HttpServletRequest request){
		 return getUserSession(request,CommonConst.SYS_COOKIE_NAME);
	 }
	 /**
	  * 得到用户session信息
	  * @param request
	  * @param cName
	  * @return
	  */
	 public  UserSession getUserSession(HttpServletRequest request,String cName){ 
		 UserSession result=null;
		 Cookie temp=null; 
		 try{ 
			 temp=getCookie(request,cName);
			 if(temp!=null){
			      //解密cookie
				 result=decryCookie(temp);
			 }
		 }
	     catch(Exception ex){
	    	 ex.printStackTrace();
	     } 
		 return result; 
	 }
	 /**
	  * 加密cookie
	  * @param userSession
	  * @return
	  */
	 public  String encryCookie(UserSession userSession){
		 //1 renderOrg+"$$"+userCode+“$$"+md5后的userpassword+"$$"+两次userCode+renderOrg数据
		 //2然后把整个字符串加密
		 StringBuffer temp=new StringBuffer("");
		 StringBuffer result=new StringBuffer("");
		 if(userSession!=null){
			 temp.append(DesEncryUtils.getInstance().getEncString(userSession.getRenderOrg())); 
			 temp.append(CommonConst.SYS_NAME_SPLIT);
			 temp.append(DesEncryUtils.getInstance().getEncString(userSession.getUserCode())); 
			 temp.append(CommonConst.SYS_NAME_SPLIT);
			 String md5Password=MD5.getMD5(userSession.getUserPassword());
			 temp.append(DesEncryUtils.getInstance().getEncString(md5Password)); 
			 temp.append(CommonConst.SYS_NAME_SPLIT);
			 md5Password=MD5.getMD5MD5(userSession.getUserCode()+userSession.getRenderOrg());//作为key
			 temp.append(DesEncryUtils.getInstance().getEncString(md5Password));
			 result.append(DesEncryUtils.getInstance().getEncString(temp.toString()));
		 }
		 return result.toString();
	 }
	 
	 /**
	  * 解密cookie
	  * @param cookie
	  * @return
	  */
	 private UserSession decryCookie(Cookie cookie){
		 UserSession result=null;
		 if (CommonConst.SYS_COOKIE_NAME.equals(cookie.getName())==true) { 
			 //得到cookie数值
            String temp=cookie.getValue(); 
            result=decryCookie(temp);
          } 
		 return result;
	 }
	 /**
	  * 解密cookie数据
	  * @param cookieValue
	  * @return
	  */
	 public UserSession decryCookie(String cookieValue){
		 UserSession result=null;
		  String temp=DesEncryUtils.getInstance().getDesString(cookieValue);
          String[] arr=StringUtils.strToArray(temp, CommonConst.SYS_NAME_SPLIT); 
          if(arr!=null&&arr.length>0){
          	DesEncryUtils instance=DesEncryUtils.getInstance();
          	if(arr.length>3){
              	result=new UserSession();
          		result.setRenderOrg(instance.getDesString(arr[0]));
          		result.setUserCode(instance.getDesString(arr[1]));
          		result.setMd5UserPassword(instance.getDesString(arr[2]));
          		result.setCookieKey(instance.getDesString(arr[3]));
          	}
          } 
          return result;
	 }
	 /**
	  * 判断cookiekey是否有效
	  * @param userSession
	  * @return
	  */
	 public boolean validCookieKey(UserSession userSession){
		boolean result=false;
		if(userSession!=null){
			if(StringUtils.isEmpty(userSession.getRenderOrg())==false
				&&StringUtils.isEmpty(userSession.getUserCode())==false){
				String cookieKey=MD5.getMD5MD5(userSession.getUserCode()+userSession.getRenderOrg());
				if(cookieKey!=null){
					if(cookieKey.equals(userSession.getCookieKey())==true){
						result=true;
					}
				}
			}
		}
		return result;
	 }
	 
	 /**
	  * 判断cookiekey是否有效
	  * @param userCode
	  * @param renderOrg
	  * @param cookieKey
	  * @return
	  */
	 public boolean validCookieKey(String userCode,String renderOrg,String srcCookieKey){
		boolean result=false; 
		if(StringUtils.isEmpty(userCode)==false
				&&StringUtils.isEmpty(renderOrg)==false
				&&StringUtils.isEmpty(srcCookieKey)==false){
				String cookieKey=MD5.getMD5MD5(userCode+renderOrg);
				if(cookieKey!=null){
					if(cookieKey.equals(srcCookieKey)==true){
						result=true;
					}
				}
			} 
		return result;
	 }
	 
	 
	 public static void main(String[] args){
		 CookieUtils c=new CookieUtils();
		 UserSession user=new UserSession();
		 user.setRenderOrg("你好123456");
		 user.setUserCode("$!@#$我们");
		 user.setUserPassword("托尔斯泰阿收费");
		 String temp=c.encryCookie(user);
  		System.out.println(temp);
 		System.out.println("");
  		 temp=DesEncryUtils.getInstance().getDesString(temp);
   		System.out.println(temp);
		 String[] arr=StringUtils.strToArray(temp, CommonConst.SYS_NAME_SPLIT); 
         if(arr!=null&&arr.length>0){
         	if(arr.length>3){
         		UserSession result=new UserSession();
         		result.setRenderOrg(arr[0]);
         		result.setUserCode(arr[1]);
         		result.setUserPassword(arr[2]);
         		result.setCookieKey(arr[3]);
         		System.out.println("");
         		System.out.println(result.getRenderOrg());
         		System.out.println(result.getUserCode());
         		System.out.println(result.getUserPassword());
         		System.out.println(result.getCookieKey());
         		System.out.println(DesEncryUtils.getInstance().getDesString(result.getRenderOrg()));
         		System.out.println(DesEncryUtils.getInstance().getDesString(result.getUserCode()));
         		System.out.println(DesEncryUtils.getInstance().getDesString(result.getUserPassword()));
         		System.out.println(DesEncryUtils.getInstance().getDesString(result.getCookieKey()));
         	}
         } 
	 }
}
