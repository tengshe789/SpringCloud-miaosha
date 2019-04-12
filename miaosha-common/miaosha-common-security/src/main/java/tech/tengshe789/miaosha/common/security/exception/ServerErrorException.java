package tech.tengshe789.miaosha.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

/**
 * @author lengleng
 * @date 2018/7/8
 */
@JsonSerialize(using = MiaoshaAuth2ExceptionSerializer.class)
public class ServerErrorException extends MiaoshaAuth2Exception {

	public ServerErrorException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "server_error";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

}
