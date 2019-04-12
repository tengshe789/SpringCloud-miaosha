package tech.tengshe789.miaosha.common.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;

/**
 * @program: -miaosha
 * @author: tEngSHe789
 * @create: 2019-03-31 22:16
 **/
@Slf4j
public class MiaoshaSecurityBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	/**
	 * 根据注解值动态注入资源服务器的相关属性
	 *
	 * @param metadata 注解信息
	 * @param registry 注册器
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
		if (registry.isBeanNameInUse(SecurityConstants.RESOURCE_SERVER_CONFIGURER)) {
			log.warn("本地存在资源服务器配置，覆盖默认配置:" + SecurityConstants.RESOURCE_SERVER_CONFIGURER);
			return;
		}

		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(MiaoshaResourceServerConfigurerAdapter.class);
		registry.registerBeanDefinition(SecurityConstants.RESOURCE_SERVER_CONFIGURER, beanDefinition);

	}
}
