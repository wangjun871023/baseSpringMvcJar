package com.macrosoft.core.cookies;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

 
/**
 * 
 * @author Administrator
 * 
 */
public final class DesEncryUtils {
    /**
     * md5算法
     */
	private final static String DES_ENCRY="DES";
	/**
	 * 
	 */
    private static String SYS_ENCODE_UTF8="UTF-8";
	//des密钥 密钥种子
	private  String keySeed="8cJdm76WbOaUf_hg5xvYzEeQ24PR9K=";
	private int keySize=128;

	private SecretKey  secretKey;
    private static DesEncryUtils instance=new DesEncryUtils(); 
    /**
     * 
     */
	private DesEncryUtils(){ 
		//getSecretKey();
	}
	/**
	 * 
	 * @return
	 */
	public static DesEncryUtils getInstance(){
		return instance;
	}
	
	/**
	 * 根据参数生成KEY
	 * 
	 * @param strKey
	 */
	private void getSecretKey() {
		KeyGenerator _generator=null;
		try {
			 //先得到密钥
			_generator=KeyGenerator.getInstance(DES_ENCRY);
			SecureRandom secureRandom= new SecureRandom(keySeed.getBytes(SYS_ENCODE_UTF8)); 
			 // 生成密匙    DES算法要求有一个可信任的随机数源 
			_generator.init(secureRandom);
			this.secretKey=_generator.generateKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{ 
			_generator=null;
		}
	}
	/**   
     * 转换密钥 
     * 
     * @param key
     * @return
     * @throws Exception
     */
    private  Key toKey(byte[] k) throws Exception {
    	DESKeySpec dks=null;
    	SecretKeyFactory keyFactory=null;
    	SecretKey key=null;
    	try{
    		 dks= new DESKeySpec(k);
             keyFactory= SecretKeyFactory.getInstance(DES_ENCRY);
             key= keyFactory.generateSecret(dks);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	finally{
    		 dks=null;
    	     keyFactory=null;
    	}
        return key;
    } 

	/**
	 * 加密String明文输入,String密文输出
	 * 
	 * @param strMing
	 * @return
	 */
	public String getEncString(String strMing) { 
		String result="";  
		 byte[] data=null;
		 byte[] encryptedData=null;
		try { 
	        // 现在，获取数据并加密
	        data=strMing.getBytes(SYS_ENCODE_UTF8);
	        // 正式执行加密操作
	        encryptedData=getEncCode(data);  
			//result=Base64Util.encodeString(new String(encryptedData));
	        result=new String(Base64Util.encode(encryptedData));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			data=null;
			encryptedData=null;
		}
		return result;
	}

	/**
	 * 解密 以String密文输入,String明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	public String getDesString(String strMing) {
		String result=""; 
		byte[] data=null;
		byte[] encryptedData=null;
		try { 
	        // 现在，获取数据并加密
	        data=Base64Util.decode(strMing); 
	        // 正式执行解密操作
	        encryptedData=getDesCode(data); 
			//result=new String(Base64Util.encode(encryptedData));
	        result=new String(encryptedData);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return result;
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina=null;
		Cipher cipher=null; 
		SecureRandom random=null;
		DESKeySpec desKey=null;
		SecretKeyFactory keyFactory=null;
		SecretKey securekey=null;
		try {
	        random=SecureRandom.getInstance("SHA1PRNG");
	        random.setSeed(keySeed.getBytes(SYS_ENCODE_UTF8)); 
	        desKey= new DESKeySpec(keySeed.getBytes(SYS_ENCODE_UTF8));   
	        //创建一个密匙工厂，然后用它把DESKeySpec转换成    
	        keyFactory= SecretKeyFactory.getInstance(DES_ENCRY);   
	        securekey= keyFactory.generateSecret(desKey);    
	        //Cipher对象实际完成加密操作    
	         cipher= Cipher.getInstance(DES_ENCRY);    
	        //用密匙初始化Cipher对象    
	        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);    
			byteFina=cipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			random=null;
			 desKey=null;
			 keyFactory=null;
			 securekey=null;
			cipher=null;
		}
		return byteFina;
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private byte[] getDesCode(byte[] byteD) {
		Cipher cipher=null;
		byte[] byteFinal=null; 
		SecureRandom random=null;
		DESKeySpec desKey=null;
		SecretKeyFactory keyFactory=null;
		SecretKey securekey=null;
		try {
		    // DES算法要求有一个可信任的随机数源    
	        random=SecureRandom.getInstance("SHA1PRNG");
	        random.setSeed(keySeed.getBytes(SYS_ENCODE_UTF8));
	        // 创建一个DESKeySpec对象     
	        desKey= new DESKeySpec(keySeed.getBytes(SYS_ENCODE_UTF8));   
	        // 创建一个密匙工厂    
	        keyFactory= SecretKeyFactory.getInstance(DES_ENCRY);  
	        // 将DESKeySpec对象转换成SecretKey对象    
	        securekey= keyFactory.generateSecret(desKey);  
	        // Cipher对象实际完成解密操作 
            cipher= Cipher.getInstance(DES_ENCRY);
	        // 用密匙初始化Cipher对象     
	        cipher.init(Cipher.DECRYPT_MODE, securekey, random);   
			byteFinal=cipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher=null;
			 desKey=null;
			 keyFactory=null;
			 securekey=null;
			cipher=null;
		}
		return byteFinal;

	}
	 

  

	public static void main(String[] args){ 
        //待加密内容    
            String str="@你好1234|}{PO:LK;；；；；asdf、。，"; 
            /*
             * 3DES加密
             */
			DesEncryUtils t=DesEncryUtils.getInstance(); 

            System.out.println("3DES加密以前:"+str);
            String temp=t.getEncString(str);
            System.out.println("3DES加密以后:"+temp);
            System.out.println("3DES解密以后:"+t.getDesString(temp));
  
	}
}