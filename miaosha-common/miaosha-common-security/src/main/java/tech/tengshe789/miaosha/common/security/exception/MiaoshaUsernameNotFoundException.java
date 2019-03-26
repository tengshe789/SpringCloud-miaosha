package tech.tengshe789.miaosha.common.security.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tech.tengshe789.miaosha.common.core.constants.CodeMsgConstants;

/**
 * @program: -miaosha
 * @description: 用户找不到异常
 * @author: tEngSHe789
 * @create: 2019-03-21 16:00
 **/
public class MiaoshaUsernameNotFoundException extends UsernameNotFoundException {
	public MiaoshaUsernameNotFoundException() {
		super(CodeMsgConstants.MIAOSHA_USERNAME_NOT_FOUND.getMsg());
	}
}
