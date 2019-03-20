package tech.tengshe789.miaosha.common.core.util;

/**
 * @program: -miaosha
 * @description: EmailUtils
 * @author: tEngSHe789
 * @create: 2019-03-19 17:58
 **/
public class EmailUtils {
	/**
	 *  网易邮箱 @163.com @126.com
	 移动邮箱 @139.com
	 搜狐邮箱 @sohu.com
	 qq邮箱  @qq.com
	 189邮箱 @189.cn
	 tom邮箱 @tom.com
	 阿里邮箱 @aliyun.com
	 新浪邮箱 @sina.com
	 等等
	 * @param email
	 * @return
	 */
	public static String getEmailtypeBy(String email){
		String emailtye = "其他邮箱用户";
		if(email.contains("@163.com")||email.contains("@126.com")){
			emailtye = "网易邮箱用户";
		}else if (email.contains("@139.com")){
			emailtye = "移动邮箱用户";
		}else if (email.contains("@sohu.com")){
			emailtye = "搜狐邮箱用户";
		}else if (email.contains("@qq.com")){
			emailtye = "qq邮箱用户";
		}else if (email.contains("@189.cn")){
			emailtye = "189邮箱用户";
		}else if (email.contains("@tom.com")){
			emailtye = "tom邮箱用户";
		}else if (email.contains("@aliyun.com")){
			emailtye = "阿里邮箱用户";
		}else if (email.contains("@sina.com")){
			emailtye = "新浪邮箱用户";
		}
		return emailtye;
	}
}

