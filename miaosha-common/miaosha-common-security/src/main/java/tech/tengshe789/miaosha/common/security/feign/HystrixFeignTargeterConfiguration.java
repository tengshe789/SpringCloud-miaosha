package tech.tengshe789.miaosha.common.security.feign;

import feign.hystrix.HystrixFeign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @program: -miaosha
 * @description: HystrixFeignTargeter 配置
 * @author: tEngSHe789
 * @create: 2019-03-21 14:48
 **/
@Configuration
@ConditionalOnClass(HystrixFeign.class)
@ConditionalOnProperty("feign.hystrix.enabled")
public class HystrixFeignTargeterConfiguration {

}

