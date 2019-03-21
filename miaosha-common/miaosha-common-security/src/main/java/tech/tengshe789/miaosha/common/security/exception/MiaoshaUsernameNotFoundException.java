package tech.tengshe789.miaosha.common.security.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @program: -miaosha
 * @description: 用户找不到异常
 * @author: tEngSHe789
 * @create: 2019-03-21 16:00
 **/
public class MiaoshaUsernameNotFoundException extends UsernameNotFoundException {
	public MiaoshaUsernameNotFoundException() {
		super("用户不存在");
	}
}
