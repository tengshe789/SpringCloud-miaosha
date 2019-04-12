package tech.tengshe789.miaosha.sys.api.feign.fallback;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.sys.api.dto.UserDTO;
import tech.tengshe789.miaosha.sys.api.dto.UserInfo;
import tech.tengshe789.miaosha.sys.api.feign.RemoteUserService;

@Slf4j
@Component
public class RemoteUserServiceFallbackImpl implements RemoteUserService {
	@Setter
	private Throwable cause;

	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     内外标志
	 * @return R
	 */
	@Override
	public Result<UserInfo> info(String username, String from) {
		log.error("feign 查询用户信息失败:{}", username, cause);
		return null;
	}

	@Override
	public Result<UserInfo> social(String inStr, @RequestHeader(SecurityConstants.FROM) String from) {
		log.error("feign 查询用户信息失败:{}", inStr, cause);
		return null;
	}

	@Override
	public Result<Boolean> register(UserDTO userDto) {
		log.error("feign 注册用户失败:{}", userDto.getUsername(), cause);
		return null;
	}
}
