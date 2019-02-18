package tech.tengshe789.miaosha.common.core.annotation;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import tech.tengshe789.miaosha.common.core.swagger.SwaggerAutoConfiguration;

import java.lang.annotation.*;

/**
 * @program: -miaosha
 * @description:  开启 miaosha swagger
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-11 15:08
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(SwaggerAutoConfiguration.class)
@EnableConfigurationProperties
public @interface EnableMiaoshaSwagger {
}
