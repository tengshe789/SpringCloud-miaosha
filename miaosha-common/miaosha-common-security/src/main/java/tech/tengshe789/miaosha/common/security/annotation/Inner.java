package tech.tengshe789.miaosha.common.security.annotation;

import java.lang.annotation.*;

/**
 * @program: -miaosha
 * @description: 服务调用不鉴权注解
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Inner {
	/**
	 * 是否AOP统一处理
	 *
	 * @return false, true
	 */
	boolean value() default true;

	/**
	 * 需要特殊判空的字段(预留)
	 *
	 * @return {}
	 */
	String[] field() default {};
}
