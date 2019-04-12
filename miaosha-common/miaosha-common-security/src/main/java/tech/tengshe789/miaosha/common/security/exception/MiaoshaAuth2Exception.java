package tech.tengshe789.miaosha.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author lengleng
 * @date 2018/7/8
 * 自定义OAuth2Exception
 */
@JsonSerialize(using = MiaoshaAuth2ExceptionSerializer.class)
public class MiaoshaAuth2Exception extends OAuth2Exception {
	@Getter
	private String errorCode;

	public MiaoshaAuth2Exception(String msg) {
		super(msg);
	}

	public MiaoshaAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
}
