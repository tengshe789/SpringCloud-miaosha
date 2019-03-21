package tech.tengshe789.miaosha.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaosha.common.security.handler.AbstractAuthenticationSuccessEventHandler;

/**
 * @program: -miaosha
 * @description: 认证成功事件处理器
 * @author: tEngSHe789
 * @create: 2019-03-21 10:56
 **/
@Slf4j
@Component
public class AuthenticationSuccessEventHandler extends AbstractAuthenticationSuccessEventHandler {

	/**
	 * 处理登录成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 *
	 * @param authentication 登录对象
	 */
	@Override
	public void handle(Authentication authentication) {
		log.info("用户：{} 登录成功", authentication.getPrincipal());
	}
}
