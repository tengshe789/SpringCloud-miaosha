package tech.tengshe789.miaosha.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

/**
 * @author lengleng
 * @date 2018/7/8
 */
@JsonSerialize(using = MiaoshaAuth2ExceptionSerializer.class)
public class MethodNotAllowedException extends MiaoshaAuth2Exception {

	public MethodNotAllowedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "method_not_allowed";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}
