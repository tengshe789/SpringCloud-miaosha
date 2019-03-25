package tech.tengshe789.miaosha.gateway.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import tech.tengshe789.miaosha.gateway.handler.HystrixFallbackHandler;
import tech.tengshe789.miaosha.gateway.handler.SwaggerResourceHandler;
import tech.tengshe789.miaosha.gateway.handler.SwaggerSecurityHandler;
import tech.tengshe789.miaosha.gateway.handler.SwaggerUiHandler;

/**
 * @program: -miaosha
 * @description: 路由配置信息
 * @author: tEngSHe789
 * @create: 2019-03-25 10:30
 **/
@Slf4j
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {
	private final HystrixFallbackHandler hystrixFallbackHandler;
	private final SwaggerResourceHandler swaggerResourceHandler;
	private final SwaggerSecurityHandler swaggerSecurityHandler;
	private final SwaggerUiHandler swaggerUiHandler;

	@Bean
	public RouterFunction routerFunction() {
		return RouterFunctions.route(
			RequestPredicates.path("/fallback")
				.and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), hystrixFallbackHandler)
			.andRoute(RequestPredicates.GET("/swagger-resources")
				.and(RequestPredicates.accept(MediaType.ALL)), swaggerResourceHandler)
			.andRoute(RequestPredicates.GET("/swagger-resources/configuration/ui")
				.and(RequestPredicates.accept(MediaType.ALL)), swaggerUiHandler)
			.andRoute(RequestPredicates.GET("/swagger-resources/configuration/security")
				.and(RequestPredicates.accept(MediaType.ALL)), swaggerSecurityHandler);
		//TODO 验证码配置

	}

}
