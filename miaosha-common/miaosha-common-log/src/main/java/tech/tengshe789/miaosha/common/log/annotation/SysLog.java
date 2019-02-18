package tech.tengshe789.miaosha.common.log.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @program: -miaosha
 * @description: 操作日志
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SysLog {

	/**
	 * 描述
	 *
	 * @return {String}
	 */
	String value();
}
