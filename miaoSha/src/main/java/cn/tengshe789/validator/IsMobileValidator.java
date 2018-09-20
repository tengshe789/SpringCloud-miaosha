package cn.tengshe789.validator;

import cn.tengshe789.util.ValidatorUtil;
import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author tengshe789
 *
 * 判断手机号码格式是否正确
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {
    private boolean required = false;

    @Override
    public void initialize(IsMobile isMobile) {
        required = isMobile.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required){
            return ValidatorUtil.isMoblie(s);
        }else if (StringUtils.isEmpty(s)){
            return true;
        }else {
            return ValidatorUtil.isMoblie(s);
        }
    }
}
