package com.macrosoft.core.cookies;

/**
 * A Base64 Encoder/Decoder.
 * 
 * <p>
 * This class is used to encode and decode data in Base64 format as described in
 * RFC 1521. 
 */

public final class Base64Util {
	/**
	 * 
	 */
    private static String SYS_ENCODE_UTF8="UTF8";
	//Mapping table from 6-bit nibbles to Base64 characters.
	private static char[] map1=new char[64];
	static {
		int i=0;
		for(char c='A'; c <='Z'; c++)
			map1[i++]=c;
		for(char c='a'; c <='z'; c++)
			map1[i++]=c;
		for(char c='0'; c <='9'; c++)
			map1[i++]=c;
		map1[i++]='+';
		map1[i++]='/';
	}

	//Mapping table from Base64 characters to 6-bit nibbles.
	private static byte[] map2=new byte[128];
	static {
		for(int i=0; i<map2.length; i++)
			map2[i]=-1;
		for(int i=0; i<64; i++)
			map2[map1[i]]=(byte) i;
	}

	/**
	 * Encodes a string into Base64 format. No blanks or line breaks are
	 * inserted.
	 * 
	 * @param s
	 *            a String to be encoded.
	 * @return A String with the Base64 encoded data.
	 */
	public static String encodeString(String s) {
		try{
		return new String(encode(s.getBytes(SYS_ENCODE_UTF8)));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Encodes a byte array into Base64 format. No blanks or line breaks are
	 * inserted.
	 * 
	 * @param in
	 *            an array containing the data bytes to be encoded.
	 * @return A character array with the Base64 encoded data.
	 */
	public static char[] encode(byte[] in) {
		return encode(in,in.length);
	}

	/**
	 * Encodes a byte array into Base64 format. No blanks or line breaks are
	 * inserted.
	 * 
	 * @param in
	 *            an array containing the data bytes to be encoded.
	 * @param iLen
	 *            number of bytes to process in <code>in</code>.
	 * @return A character array with the Base64 encoded data.
	 */
	public static char[] encode(byte[] in,int iLen) {
		int oDataLen=(iLen * 4 + 2) / 3; //output length without padding
		int oLen=((iLen + 2) / 3) * 4; //output length including padding
		char[] out=new char[oLen];
		int ip=0;
		int op=0;
		while(ip <iLen) {
			int i0=in[ip++] & 0xff;
			int i1=ip <iLen ? in[ip++] & 0xff : 0;
			int i2=ip <iLen ? in[ip++] & 0xff : 0;
			int o0=i0 >>> 2;
			int o1=((i0 & 3) <<4) |(i1 >>> 4);
			int o2=((i1 & 0xf) <<2) |(i2 >>> 6);
			int o3=i2 & 0x3F;
			out[op++]=map1[o0];
			out[op++]=map1[o1];
			out[op]=op <oDataLen ? map1[o2] : '=';
			op++;
			out[op]=op <oDataLen ? map1[o3] : '=';
			op++;
		}
		return out;
	}

	/**
	 * Decodes a string from Base64 format.
	 * 
	 * @param s
	 *            a Base64 String to be decoded.
	 * @return A String containing the decoded data.
	 * @throws IllegalArgumentException
	 *             if the input is not valid Base64 encoded data.
	 */
	public static String decodeString(String s) {
		return new String(decode(s));
	}

	/**
	 * Decodes a byte array from Base64 format.
	 * 
	 * @param s
	 *            a Base64 String to be decoded.
	 * @return An array containing the decoded data bytes.
	 * @throws IllegalArgumentException
	 *             if the input is not valid Base64 encoded data.
	 */
	public static byte[] decode(String s) {
		return decode(s.toCharArray());
	}

	/**
	 * Decodes a byte array from Base64 format. No blanks or line breaks are
	 * allowed within the Base64 encoded data.
	 * 
	 * @param in
	 *            a character array containing the Base64 encoded data.
	 * @return An array containing the decoded data bytes.
	 * @throws IllegalArgumentException
	 *             if the input is not valid Base64 encoded data.
	 */
	public static byte[] decode(char[] in) {
		int iLen=in.length;
		if(iLen % 4!=0)
			throw new IllegalArgumentException(
					"Length of Base64 encoded input string is not a multiple of 4.");
		while(iLen > 0&&in[iLen - 1]=='=')
			iLen--;
		int oLen=(iLen * 3) / 4;
		byte[] out=new byte[oLen];
		int ip=0;
		int op=0;
		while(ip <iLen) {
			int i0=in[ip++];
			int i1=in[ip++];
			int i2=ip <iLen ? in[ip++] : 'A';
			int i3=ip <iLen ? in[ip++] : 'A';
			if(i0 > 127 ||i1 > 127 ||i2 > 127 ||i3 > 127)
				throw new IllegalArgumentException(
						"Illegal character in Base64 encoded data.");
			int b0=map2[i0];
			int b1=map2[i1];
			int b2=map2[i2];
			int b3=map2[i3];
			if(b0 <0 ||b1 <0 ||b2 <0 ||b3 <0)
				throw new IllegalArgumentException(
						"Illegal character in Base64 encoded data.");
			int o0=(b0 <<2) |(b1 >>> 4);
			int o1=((b1 & 0xf) <<4) |(b2 >>> 2);
			int o2=((b2 & 3) <<6) | b3;
			out[op++]=(byte) o0;
			if(op <oLen)
				out[op++]=(byte) o1;
			if(op <oLen)
				out[op++]=(byte) o2;
		}
		return out;
	}

	private Base64Util() {

	}

	public static void main(String[] args) {
		try{
	 
		System.out.println(Base64Util.encodeString("您好"));
		System.out.println(Base64Util.encodeString("您好吸塑车间主要产品是各种PVC（聚氯乙烯）、PS（聚苯乙烯）、PET（聚对苯二甲酸乙二酯）、ABS（丙烯腈，丁二烯，苯乙烯三者的共聚物）、PE（聚乙烯）、PP（聚丙烯）等材质的塑料包装。产品可用于各种礼品、电子电器、玩具、医疗器械、医药、食品等内外包装。另外还有PVC盒，PET盒，塑料盒，PVC折盒，化妆品包装，圆筒，套筒，透明包装，PP盒，塑料包装，包装制品，包装盒，折盒，印刷胶盒（丝网／柯式印刷）PVC印刷，椭圆筒，吸塑，啤片，苹果批，天地盒，礼品包装，日用品包装，化妆品包装，药品包装，食品包装，文具包装，玩具包装。并可对塑料制品表面进行植绒、镀金、皮纹处理。"));
		System.out.println(Base64Util.decodeString("xPq6w878y9yztbzk1vfSqrL6xrfKx7j31tZQVkOjqL7bwsjS0s+po6mholBTo6i+27G90tLPqaOpoaJQRVSjqL7bttSxvbb+vNfL4dLStv71paOpoaJBQlOjqLH7z6nr5qOstqG2/s+po6yxvdLSz6nI/dXftcS5sr7bzu+jqaGiUEWjqL7b0tLPqaOpoaJQUKOovtux+8+po6m1yLLE1sq1xMvcwc+w/NewoaOy+sa3v8nTw9PauPfW1sDxxrehorXn19O158b3oaLN5r7foaLSvcHGxvfQtaGi0r3SqaGiyrPGt7XIxNrN4rD817Cho8HtzeK7udPQUFZDutCjrFBFVLrQo6zL3MHPutCjrFBWQ9XbutCjrLuv17HGt7D817CjrNSyzbKjrMzXzbKjrM24w/ew/Newo6xQULrQo6zL3MHPsPzXsKOssPzXsNbGxrejrLD817C60KOs1du60KOs06HLor26utCjqMu/zfijr7/Cyr3Tocuio6lQVkPTocuio6zN1tSyzbKjrM78y9yjrMahxqyjrMa7ufvF+qOszOy12LrQo6zA8ca3sPzXsKOsyNXTw8a3sPzXsKOsu6/Xsca3sPzXsKOs0qnGt7D817CjrMqzxrew/Newo6zOxL7fsPzXsKOszea+37D817Cho7Kiv8m21Mvcwc/Wxsa3se3D5r340NDWssjeoaK2xr3woaLGpM7GtKbA7aGj"));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
