package tech.tengshe789.miaosha.sys.biz.service;

import tech.tengshe789.miaosha.common.core.result.Result;

/**
 * @author lengleng
 */
public interface MobileService {
	/**
	 * 发送手机验证码
	 *
	 * @param mobile mobile
	 * @return code
	 */
	Result<Boolean> sendSmsCode(String mobile);
}
