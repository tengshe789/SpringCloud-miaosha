package tech.tengshe789.miaosha.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @program: -miaosha
 * @description: 自定义basic认证，针对特殊场景使用
 * @author: tEngSHe789
 * @create: 2019-03-31 21:43
 **/
@Slf4j
@Component
public class HttpBasicGatewayFilter extends AbstractGatewayFilterFactory {
	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			if (hasAuth(exchange)) {
				return chain.filter(exchange);
			} else {
				ServerHttpResponse response = exchange.getResponse();
				response.setStatusCode(HttpStatus.UNAUTHORIZED);
				response.getHeaders().add(HttpHeaders.WWW_AUTHENTICATE, "Basic Realm=\"miaosha\"");
				return response.setComplete();
			}
		};
	}

	/**
	 * 简单的basic认证
	 *
	 * @param exchange 上下文
	 * @return 是否有权限
	 */
	private boolean hasAuth(ServerWebExchange exchange) {
		ServerHttpRequest request = exchange.getRequest();
		String auth = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		log.info("Basic认证信息为：{}", auth);
		return true;
	}

}

