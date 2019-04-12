package tech.tengshe789.miaosha.common.security.feign;

import cn.hutool.core.collection.CollUtil;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.security.oauth2.client.AccessTokenContextRelay;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;

import java.util.Collection;

/**
 * @program: -miaosha
 * @description: 扩展OAuth2FeignRequestInterceptor
 * @author: tEngSHe789
 * @create: 2019-03-21 14:29
 **/
@Slf4j
public class FeignClientInterceptor extends OAuth2FeignRequestInterceptor {
	private final OAuth2ClientContext oAuth2ClientContext;
	private final AccessTokenContextRelay accessTokenContextRelay;

	/**
	 * Default constructor which uses the provided OAuth2ClientContext and Bearer tokens
	 * within Authorization header
	 *
	 * @param oAuth2ClientContext     provided context
	 * @param resource                type of resource to be accessed
	 * @param accessTokenContextRelay
	 */
	public FeignClientInterceptor(OAuth2ClientContext oAuth2ClientContext
		, OAuth2ProtectedResourceDetails resource, AccessTokenContextRelay accessTokenContextRelay) {
		super(oAuth2ClientContext, resource);
		this.oAuth2ClientContext = oAuth2ClientContext;
		this.accessTokenContextRelay = accessTokenContextRelay;
	}


	/**
	 * fein 拦截器将本服务的token 通过copyToken的形式传递给下游服务
	 * Create a template with the header of provided name and extracted extract
	 * 1. 如果使用 非web 请求，header 区别
	 * 2. 根据authentication 还原请求token
	 *
	 * @param template
	 */
	@Override
	public void apply(RequestTemplate template) {
		Collection<String> fromHeader = template.headers().get(SecurityConstants.FROM);
		if (CollUtil.isNotEmpty(fromHeader) && fromHeader.contains(SecurityConstants.FROM_IN)) {
			return;
		}

		accessTokenContextRelay.copyToken();
		if (oAuth2ClientContext != null
			&& oAuth2ClientContext.getAccessToken() != null) {
			super.apply(template);
		}
	}
}
