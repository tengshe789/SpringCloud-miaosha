package tech.tengshe789.miaosha.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: -miaosha
 * @author: tEngSHe789
 * @create: 2019-03-21 10:36
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/register").setViewName("register");
		registry.addViewController("/change-password").setViewName("changePassword");
	}
}
