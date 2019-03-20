package tech.tengshe789.miaosha.common.core.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
/**
 * @program: -miaosha
 * @description: 国际化配置
 * @author: tEngSHe789
 * @create: 2019-03-20 16:37
 **/
@Configuration
public class MessageSourceConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource
			= new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:i18n/messages");
		return messageSource;
	}
}

