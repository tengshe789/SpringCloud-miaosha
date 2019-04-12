package tech.tengshe789.miaosha.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

/**
 * @author lengleng
 * @date 2018/7/8
 */
@JsonSerialize(using = MiaoshaAuth2ExceptionSerializer.class)
public class UnauthorizedException extends MiaoshaAuth2Exception {

	public UnauthorizedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "unauthorized";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.UNAUTHORIZED.value();
	}

}
