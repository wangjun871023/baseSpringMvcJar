package com.macrosoft.common;



/**
 * 执行临时表到业务表的逻辑类的接口定义
 * 命令接口
 * @author 呆呆
 *
 */
public interface CommandInterface {
	
	/**
	 * 执行临时表到业务表的逻辑
	 * @param context
	 * @return 成功返回1 失败返回 -1
	 * @throws Exception
	 */
   public int service(Context context) throws Exception ;
}
