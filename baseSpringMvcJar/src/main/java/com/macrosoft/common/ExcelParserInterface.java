package com.macrosoft.common;

import java.util.Hashtable;

/**
 * excel文件解析
 * @author 呆呆
 *
 */
public interface ExcelParserInterface {
	/**
	 * 解析excel文件 
	 * @param excelForm 默认参数名为file
	 * @return Hashtable中的key有 success ，errorCode,errorMsg
	 */
	public Hashtable parse(ExcelForm excelForm)throws Exception;
}
