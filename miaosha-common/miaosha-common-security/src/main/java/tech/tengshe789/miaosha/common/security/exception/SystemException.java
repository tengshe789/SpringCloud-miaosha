package tech.tengshe789.miaosha.common.security.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tech.tengshe789.miaosha.common.core.constants.CodeMsgConstants;

/**
 * @program: -miaosha
 * @description: 系统异常
 * @author: tEngSHe789
 * @create: 2019-03-28 13:07
 **/
public class SystemException extends RuntimeException {
	public SystemException() {
		super(CodeMsgConstants.SERVER_ERROR.getMsg());
	}
}
