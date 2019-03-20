package tech.tengshe789.miaosha.common.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: -miaosha
 * @description:
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-18 11:15
 **/
@Configuration
public class ThrowableConfig {
	@Bean
	public Throwable throwable () {
		return new Throwable();
	}
}
