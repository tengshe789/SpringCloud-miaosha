package tech.tengshe789.miaosha.sys.biz.handler;

import tech.tengshe789.miaosha.sys.api.dto.UserInfo;

/**
 * @program: -miaosha
 * @description: 登陆处理器
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-11 14:33
 **/
public interface LoginHandler {

	/**
	 * 通过用户传入获取唯一标识
	 *
	 * @param loginStr
	 * @return
	 */
	String identify(String loginStr);

	/**
	 * 通过openId 获取用户信息
	 *
	 * @param identify
	 * @return
	 */
	UserInfo info(String identify);

	/**
	 * 处理方法
	 *
	 * @param loginStr 登录参数
	 * @return
	 */
	UserInfo handle(String loginStr);
}
