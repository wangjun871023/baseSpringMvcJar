package com.macrosoft.common;

/**
 * Excel 表单
 * @author 呆呆
 *
 */
public class ExcelForm {
	/**
	 * 租户id
	 */
   private String comId;
   /**
    * 系统保存的文件id
    */
   private String fileId;
   /**
    * excel解析模板id
    */
   private String excelParseTemplateId;
   public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getExcelParseTemplateId() {
		return excelParseTemplateId;
	}
	public void setExcelParseTemplateId(String excelParseTemplateId) {
		this.excelParseTemplateId = excelParseTemplateId;
	} 
   
}
