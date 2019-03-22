package tech.tengshe789.miaosha.common.security.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import tech.tengshe789.miaosha.common.security.config.CustomResourceServerConfig;

import java.lang.annotation.*;

/**
 * @program: -miaosha
 * @description: miaosha开启资源服务
 * @author: tEngSHe789
 * @create: 2019-03-21 15:19
 **/
@Documented
@Inherited
@EnableResourceServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//开启@Secured 注解过滤权限
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({CustomResourceServerConfig.class})
public @interface EnableMiaoshaResourceServer {
}
