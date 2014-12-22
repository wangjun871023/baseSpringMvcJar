package com.macrosoft.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
 * 文件上传
 * @author 呆呆
 *
 */
public interface UploadFileInterface {
	/**
	 * 上传单个文件,如果是上传系统文件或不是导入数据的文件，则comId为0， uploadFileTemplateId为0
	 * @param renderId 租户id
	 * @param uploadFileTemplateId 上传文件模板id，只对excel有效
	 * @param request
	 * @param paramName 默认参数名为file
	 * @return Hashtable中的key有 success ，上传成功的主表文件id 
	 * Hashtable result=new Hashtable();
	 * result.put("success",true);
	 * result.put("main_table_key","");
	 * result.put("success",true);
	 */
	public Hashtable uploadFiles(String renderId,String uploadFileTemplateId,MultipartHttpServletRequest request,String paramName)throws Exception;

	/**
	 * 上传多个文件,如果是上传系统文件或不是导入数据的文件，则renderId为0， uploadFileTemplateId为0
	 * @param renderId 租户id
	 * @param uploadFileTemplateId 上传文件模板id，只对excel有效
	 * @param request
	 * @param paramName 默认参数名为file
	 * @return Hashtable中的key有 success ，上传成功的主表文件id 
	 * Hashtable result=new Hashtable();
	 * result.put("success",true);
	 * result.put("main_table_key","");
	 * result.put("success",true);
	 */
	public Hashtable uploadFiles(String renderId,String uploadFileTemplateId,MultipartHttpServletRequest request,String paramName[])throws Exception;

	/**
	 * 上传单个文件,如果是上传系统文件或不是导入数据的文件，则renderId为0， uploadFileTemplateId为0
	 * @param renderId 租户id
	 * @param uploadFileTemplateId 上传文件模板id，只对excel有效
	 * @param request
	 * @param paramName 默认参数名为file
	 * @return Hashtable中的key有 success ，上传成功的主表文件id 
	 * Hashtable result=new Hashtable();
	 * result.put("success",true);
	 * result.put("main_table_key","");
	 * result.put("success",true);
	 */
	public Hashtable uploadFiles(String renderId,String uploadFileTemplateId,HttpServletRequest request,String paramName)throws Exception;

	/**
	 * 上传多个文件,如果是上传系统文件或不是导入数据的文件，则renderId为0， uploadFileTemplateId为0
	 * @param renderId 租户id
	 * @param uploadFileTemplateId 上传文件模板id，只对excel有效
	 * @param request
	 * @param paramName 默认参数名为file
	 * @return Hashtable中的key有 success ，上传成功的主表文件id 
	 * Hashtable result=new Hashtable();
	 * result.put("success",true);
	 * result.put("main_table_key","");
	 * result.put("success",true);
	 */
	public Hashtable uploadFiles(String renderId,String uploadFileTemplateId,HttpServletRequest request,String paramName[])throws Exception;

	 /**
	   * 上传文件,如果是上传系统文件或不是导入数据的文件，则renderId为0， uploadFileTemplateId为0
	   * @param renderId 租户id
	   * @param uploadFileTemplateId 上传文件模板id，只对excel有效
	   * @param fileByteArr 文件字节数组
	   * @param suffix 文件后缀名
	   * @return Hashtable中的key有 success ，上传成功的文件id
	   */
	public Hashtable uploadFiles(String renderId,String uploadFileTemplateId,byte[][] uploadFileArr,String suffix)throws Exception;
	
	/**
	 * 下载文件
	 * @param renderId 租户id
	 * @param request
	 * @param response
	 * @param fileId 文件名 
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void downFile(String renderId,HttpServletRequest request,HttpServletResponse response
			,String fileId)throws UnsupportedEncodingException
			,IOException; 
	
	/**
	 * 下载文件
	 * @param renderId 租户id
	 * @param request
	 * @param response
	 * @param oFileName 文件名
	 * @param fullPath 文件路径
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void downFile(String renderId,HttpServletRequest request,HttpServletResponse response
			,String oFileName,String fullPath)throws UnsupportedEncodingException
			,IOException;
	
	/**
	 * 通过文件id得到文件列表 
	 * @param renderId 租户id
	 * @param fileId 文件id  
	 */
	public List getFile(String renderId,String fileId) ;
	/**
	 * 删除文件,包括数据库中数据和具体的文件
	 * @param renderId 租户id 
	 * @param fileId
	 */
	public boolean removeFile(String renderId,String fileId) throws Exception ;


}
