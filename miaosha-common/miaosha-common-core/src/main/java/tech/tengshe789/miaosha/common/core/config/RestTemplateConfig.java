package tech.tengshe789.miaosha.common.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: -miaosha
 * @description: RestTemplate
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-16 19:18
 **/
@Configuration
public class RestTemplateConfig {
	@Bean
	public RestTemplate restTemplate ( ) {
		return new RestTemplate();
	}
}
