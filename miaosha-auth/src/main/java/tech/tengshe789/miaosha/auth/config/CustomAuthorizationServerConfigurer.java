package tech.tengshe789.miaosha.auth.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import javax.sql.DataSource;

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

}
