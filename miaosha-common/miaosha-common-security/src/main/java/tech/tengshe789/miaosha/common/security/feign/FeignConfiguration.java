package tech.tengshe789.miaosha.common.security.feign;

import feign.Feign;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.security.oauth2.client.AccessTokenContextRelay;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * @program: -miaosha
 * @description:
 * @author: tEngSHe789
 * @create: 2019-03-21 14:27
 **/
@Configuration
@ConditionalOnClass(Feign.class)
public class FeignConfiguration {

	@Bean
	@ConditionalOnProperty("security.oauth2.client.client-id")
	public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext,
															OAuth2ProtectedResourceDetails resource,
															AccessTokenContextRelay accessTokenContextRelay) {
		return new FeignClientInterceptor(oAuth2ClientContext, resource, accessTokenContextRelay);
	}

}

