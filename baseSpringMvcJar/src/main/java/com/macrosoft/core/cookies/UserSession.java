package com.macrosoft.core.cookies;
/**
 * 用户信息bean
 * @author Administrator
 *
 */
public final class UserSession {
	/**
	 * 用户统一编码
	 */
  private String userUiid;
  /**
   * 用户姓名
   */
  private String userName;
  /**
   * 用户编码
   */
  private String userCode;

  /**
   * 用户所属组织
   */
  private String renderOrg;
  /**
   * 用户密码，原始密码
   */
  private String userPassword;
  /**
   * 用户密码，md5之后的密码
   */
  private String md5UserPassword;
  /**
   * 
   */
  private String cookieKey;
  public String getUserUiid() {
		return userUiid;
	}
	public void setUserUiid(String userUiid) {
		this.userUiid = userUiid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getRenderOrg() {
		return renderOrg;
	}
	public void setRenderOrg(String renderOrg) {
		this.renderOrg = renderOrg;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getCookieKey() {
		return cookieKey;
	}
	public void setCookieKey(String cookieKey) {
		this.cookieKey = cookieKey;
	}
	public String getMd5UserPassword() {
		return md5UserPassword;
	}
	public void setMd5UserPassword(String md5UserPassword) {
		this.md5UserPassword = md5UserPassword;
	} 
  
  
}
