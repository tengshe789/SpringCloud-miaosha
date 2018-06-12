package cn.tengshe789.util;

import org.thymeleaf.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
    //手机号码，1开头，后面有11位
    private static final Pattern mobile_pattern= Pattern.compile("1\\d{10}");

    public static boolean isMoblie(String src){
        if (StringUtils.isEmpty(src)){
            return false;
        }
        Matcher matcher=mobile_pattern.matcher(src);
        return matcher.matches();
    }

   /* public static void main(String[] args) {
        System.out.println(isMoblie("18663945752"));
        System.out.print(isMoblie("1866394575"));
    }*/
}
