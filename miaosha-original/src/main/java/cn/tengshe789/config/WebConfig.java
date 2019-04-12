package cn.tengshe789.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 给controller参数赋值
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	@Autowired
	UserArgumentResolver userArgumentResolver;

	//框架回调这个addArgumentResolvers方法，到controller，对controller里的参数进行赋值
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(userArgumentResolver);
	}
}
