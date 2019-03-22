package tech.tengshe789.miaosha.sys.biz.handler;

import tech.tengshe789.miaosha.sys.api.dto.UserInfo;

/**
 * @program: miaosha-upms
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-11 14:43
 **/
public abstract class AbstractLoginHandler implements LoginHandler {

	/**
	 * 处理方法
	 *
	 * @param loginStr 登录参数
	 * @return
	 */
	@Override
	public UserInfo handle(String loginStr) {
		String identify = identify(loginStr);
		return info(identify);
	}
}
