package tech.tengshe789.miaosha.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.tengshe789.miaosha.upms.dto.UserInfo;
import tech.tengshe789.miaosha.upms.entity.SysSocialDetails;

/**
 * 系统社交登录账号表
 *
 * @author pig code generator
 */
public interface SysSocialDetailsService extends IService<SysSocialDetails> {

	/**
	 * 绑定社交账号
	 *
	 * @param state 类型
	 * @param code  code
	 * @return
	 */
	Boolean bindSocial(String state, String code);

	/**
	 * 根据入参查询用户信息
	 *
	 * @param inStr
	 * @return
	 */
	UserInfo getUserInfo(String inStr);
}

