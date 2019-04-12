package tech.tengshe789.miaosha.common.security.config;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaosha.common.core.constants.CodeMsgConstants;
import tech.tengshe789.miaosha.common.core.constants.CommonConstants;
import tech.tengshe789.miaosha.common.core.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @program: -miaosha
 * @description: 客户端异常处理，可以根据 AuthenticationException 不同细化异常处理
 * @author: tEngSHe789
 * @create: 2019-03-31 22:18
 **/
@Slf4j
@Component
@AllArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {
	private final ObjectMapper objectMapper;

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		Result<String> result = Result.error(CodeMsgConstants.FAIL);
		if (authException != null) {
			result.setMsg(authException.getMessage());
			result.setData(authException.getMessage());
		}
		response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(result));
	}
}
