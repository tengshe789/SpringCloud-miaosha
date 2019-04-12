package tech.tengshe789.miaosha.auth.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @program: -miaosha
 * @description: Spring Security配置
 *   SecurityConfiguration 一定要在 ResourceServerConfiguration 之前
 * @author: tEngSHe789
 * @create: 2019-03-21 10:16
 **/
@Primary
@Order(90)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	ClientDetailsService clientDetailsService;

	/**
	 * form-login是spring security命名空间配置登录相关信息的标签,它包含如下属性：
	 * 1. login-page 自定义登录页url,默认为/login
	 * 2. login-processing-url 登录请求拦截的url,也就是form表单提交时指定的action
	 * 3. default-target-url 默认登录成功后跳转的url
	 * 4. always-use-default-target 是否总是使用默认的登录成功后跳转url
	 * 5. authentication-failure-url 登录失败后跳转的url
	 * 6. username-parameter 用户名的请求字段 默认为userName
	 * 7. password-parameter 密码的请求字段 默认为password
	 * 8. authentication-success-handler-ref 指向一个AuthenticationSuccessHandler用于处理认证成功的请求,不能和default-target-url
	 *
	 * @param http
	 */
	@Override
	@SneakyThrows
	protected void configure(HttpSecurity http) {
		http
			.formLogin()
			.loginPage("/token/login")
			.loginProcessingUrl("/token/form")
			.and()
			.authorizeRequests()
			.antMatchers(
				"/token/**",
				"/actuator/**",
				"/mobile/**").permitAll()
			.anyRequest().authenticated()
			.and().csrf().disable();
		//TODO 手机号登录验证处理器配置
	}

	/**
	 * 不拦截静态资源
	 *
	 * @param web
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**");
	}

	@Bean
	@Override
	@SneakyThrows
	public AuthenticationManager authenticationManagerBean() {
		return super.authenticationManagerBean();
	}

	/**
	 * https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-storage-updated
	 * Encoded password does not look like BCrypt
	 *
	 * @return PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
