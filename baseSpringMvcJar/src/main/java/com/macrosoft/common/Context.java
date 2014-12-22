package com.macrosoft.common;

/**
 * 执行临时表到业务表的参数类
 * 
 * @author 呆呆
 *
 */
public class Context {
	/**
	 * 上传模板id
	 */
	private String uploadTempateId;
	/**
	 * 用户id
	 */
	private String userId;

	public String getUploadTempateId() {
		return uploadTempateId;
	}

	public void setUploadTempateId(String uploadTempateId) {
		this.uploadTempateId = uploadTempateId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
