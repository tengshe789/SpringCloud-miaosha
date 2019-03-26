package tech.tengshe789.miaosha.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;
import tech.tengshe789.miaosha.common.core.constants.CodeMsgConstants;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.common.core.util.WebUtils;
import tech.tengshe789.miaosha.gateway.config.FilterIgnorePropertiesConfig;

/**
 * @program: -miaosha
 * @description: 终端设置不校验拦截器
 * @author: tEngSHe789
 * @create: 2019-03-25 19:26
 **/
@Slf4j
@AllArgsConstructor
public class IgnorePropertiesFilter extends AbstractGatewayFilterFactory {
	private final ObjectMapper objectMapper;
	private final FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange,chain) -> {
			ServerHttpRequest request = exchange.getRequest();

			// 不是登录请求，直接向下执行
			if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath()
				, SecurityConstants.OAUTH_TOKEN_URL, SecurityConstants.SMS_TOKEN_URL
				, SecurityConstants.SOCIAL_TOKEN_URL)) {
				return chain.filter(exchange);
			}

			// 刷新token，直接向下执行
			String grantType = request.getQueryParams().getFirst("grant_type");
			if (StrUtil.equals(SecurityConstants.REFRESH_TOKEN, grantType)) {
				return chain.filter(exchange);
			}
			//终端设置不校验
			try {
				String[] clientInfos = WebUtils.getClientId(request);
				if (filterIgnorePropertiesConfig.getClients().contains(clientInfos[0])) {
					return chain.filter(exchange);
				}
			}catch (Exception e) {
				ServerHttpResponse response = exchange.getResponse();
				response.setStatusCode(HttpStatus.PRECONDITION_REQUIRED);
				try {
					return response.writeWith(Mono.just(response.bufferFactory()
						.wrap(objectMapper.writeValueAsBytes(
							Result.error(CodeMsgConstants.INTERCEPTION_FAILURE)))));
				} catch (JsonProcessingException e1) {
					log.error("对象输出异常", e1);
				}
			}
			return chain.filter(exchange);
		};
	}
}
