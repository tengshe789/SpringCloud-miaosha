package tech.tengshe789.miaosha.auth.config;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;
import tech.tengshe789.miaosha.common.security.entity.MiaoshaUser;
import tech.tengshe789.miaosha.common.security.service.MiaoshaClientDetailsService;
import tech.tengshe789.miaosha.common.security.service.MiaoshaUserDetailsService;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: -miaosha
 * @description: 授权服务器配置
 * @author: tEngSHe789
 * @create: 2019-03-21 10:47
 **/
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class CustomAuthorizationServerConfigurer  extends AuthorizationServerConfigurerAdapter {
	private final DataSource dataSource;
	private final AuthenticationManager authenticationManagerBean;
	private final RedisConnectionFactory redisConnectionFactory;
	private final MiaoshaUserDetailsService miaoshaUserDetailsService;

	/**
	 * 配置
	 *
	 * @param endpoints
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints
			.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
			.tokenStore(tokenStore())
			.tokenEnhancer(tokenEnhancer())
			.userDetailsService(miaoshaUserDetailsService)
			.authenticationManager(authenticationManagerBean)
			.reuseRefreshTokens(false);
		//TODO 配置oauth异常处理类
	}

	/**
	 * 从数据库中验证client信息
	 *
	 * @param clients
	 */
	@Override
	@SneakyThrows
	public void configure(ClientDetailsServiceConfigurer clients) {
		MiaoshaClientDetailsService clientDetailsService = new MiaoshaClientDetailsService(dataSource);
		clientDetailsService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
		clientDetailsService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
		clients.withClientDetails(clientDetailsService);
	}


	@Override
	@SneakyThrows
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer
			.allowFormAuthenticationForClients()
			.checkTokenAccess("isAuthenticated()");
	}

	/**
	 * TokenStore是用来定制token存储策略
	 *
	 * @return
	 */
	@Bean
	public TokenStore tokenStore() {
		RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
		//封装版权信息
		tokenStore.setPrefix(SecurityConstants.MIAOSHA_PREFIX + SecurityConstants.OAUTH_PREFIX);
		tokenStore.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator() {
			@Override
			public String extractKey(OAuth2Authentication authentication) {
				return super.extractKey(authentication);
			}
		});
		return tokenStore;
	}

	/**
	 * token增强
	 *
	 * @return TokenEnhancer
	 */
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return (accessToken, authentication) -> {
			// 客户端模式不增强。
			if (SecurityConstants.CLIENT_CREDENTIALS
				.equals(authentication.getOAuth2Request().getGrantType())) {
				return accessToken;
			}
			//返回的token的信息的增强
			final Map<String, Object> additionalInfo = new HashMap<>(8);
			MiaoshaUser user = (MiaoshaUser) authentication.getUserAuthentication().getPrincipal();
			additionalInfo.put("user_id", user.getId());
			additionalInfo.put("username", user.getUsername());
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
			return accessToken;
		};
	}
}
