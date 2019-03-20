package tech.tengshe789.miaosha.common.core.validator;

import cn.hutool.core.util.StrUtil;
import tech.tengshe789.miaosha.common.core.annotation.IsMobile;
import tech.tengshe789.miaosha.common.core.util.MobileValidatorUtil;

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
            return MobileValidatorUtil.isMoblie(s);
        }else if (StrUtil.isEmpty(s)){
            return true;
        }else {
            return MobileValidatorUtil.isMoblie(s);
        }
    }
}
