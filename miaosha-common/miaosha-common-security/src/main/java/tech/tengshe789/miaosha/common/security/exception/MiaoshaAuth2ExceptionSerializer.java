package tech.tengshe789.miaosha.common.security.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;
import tech.tengshe789.miaosha.common.core.constants.CodeMsgConstants;

/**
 * @program: -miaosha
 * @description: OAuth2 异常格式化
 * @author: tEngSHe789
 * @create: 2019-03-31 22:27
 **/
public class MiaoshaAuth2ExceptionSerializer extends StdSerializer<MiaoshaAuth2Exception> {
	public MiaoshaAuth2ExceptionSerializer() {
		super(MiaoshaAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(MiaoshaAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", CodeMsgConstants.FAIL.getCode());
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		gen.writeEndObject();
	}
}
