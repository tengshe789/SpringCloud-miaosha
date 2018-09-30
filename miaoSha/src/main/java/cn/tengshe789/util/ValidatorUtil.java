package cn.tengshe789.util;

import org.thymeleaf.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tengshe789
 *
 * 判断手机号码格式是否正确
 */
public class ValidatorUtil {
    //手机号码，1开头，后面有11位
    private static final Pattern mobile_pattern= Pattern.compile("1\\d{10}");

    public static boolean isMoblie(String phoneNums){
        if (StringUtils.isEmpty(phoneNums)){
            return false;
        }
        Matcher matcher=mobile_pattern.matcher(phoneNums);
        return matcher.matches();
    }
}
