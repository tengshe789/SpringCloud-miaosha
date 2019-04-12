package tech.tengshe789.miaosha.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author lengleng
 * @date 2018/7/8
 */
@JsonSerialize(using = MiaoshaAuth2ExceptionSerializer.class)
public class InvalidException extends MiaoshaAuth2Exception {

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_exception";
	}

	@Override
	public int getHttpErrorCode() {
		return 426;
	}

}
