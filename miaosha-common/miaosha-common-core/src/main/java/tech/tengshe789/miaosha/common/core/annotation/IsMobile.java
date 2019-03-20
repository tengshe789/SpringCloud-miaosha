package tech.tengshe789.miaosha.common.core.annotation;


import tech.tengshe789.miaosha.common.core.validator.IsMobileValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @author tengshe789
 *
 * 判断手机号码格式的注解
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {IsMobileValidator.class}
)
public @interface IsMobile {
    //注解属性:声明是否需要注释依赖关系。
    boolean required() default true;

    //如果号码错误则返回信息"手机号码格式错误"
    String message() default "手机号码格式错误";

	/**
	 * 需要特殊判空的字段(预留)
	 *
	 * @return {}
	 */
	String[] field() default {};
}
