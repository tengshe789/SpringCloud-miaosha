package cn.tengshe789.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author tengshe789
 * 后端的表单密码加密功能和（弃用）
 */
public class MD5Util {
	public static String md5(String data) {
		return DigestUtils.md5Hex(data);
	}

	public static final String salt = "1a2b3c4d";

	//明文密码to表单密码
	public static String inputPassFormPass(String inputPass) {
		String s = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
		return md5(s);
	}

	//表单密码to数据库密码
	public static String formPassToDBPass(String formPass, String salt) {
		String s = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
		return md5(s);
	}

	public static String inputPassToDBPass(String inputPass, String saltDB) {
		String formPass = inputPassFormPass(inputPass);
		String dbPass = formPassToDBPass(formPass, saltDB);
		return dbPass;
	}

//    public static void main(String[] args) {
//        System.out.print(inputPassToDBPass("123456","1a2b3c4d"));//b7797cce01b4b131b433b6acf4add449
//    }
}
