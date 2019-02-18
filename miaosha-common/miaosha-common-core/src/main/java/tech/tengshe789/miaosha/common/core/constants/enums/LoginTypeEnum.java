package tech.tengshe789.miaosha.common.core.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: -miaosha
 * @description: 社交登陆类型
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-11 10:06
 **/
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
	/**
	 * 账号密码登录
	 */
	PWD("PWD", "账号密码登录"),

	/**
	 * 验证码登录
	 */
	SMS("SMS", "验证码登录"),

	/**
	 * QQ登录
	 */
	QQ("QQ", "QQ登录"),

	/**
	 * 微信登录
	 */
	WECHAT("WX", "微信登录");

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String description;
}
