package cn.tengshe789.config;

import cn.tengshe789.domain.MiaoshaUser;
import cn.tengshe789.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 从request中拿token
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    MiaoshaUserService miaoshaUserService;

    /*
    判断获取的参数是否是MiaoshaUser类型的
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //获取方法参数
        Class<?> parameterType = methodParameter.getParameterType();
        return parameterType==MiaoshaUser.class;
    }

    /*
   如果获取的参数是MiaoshaUser类型的，则从request中取一个参数的token
    */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //把request取出来
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        //把Response取出来
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        String paramToken=request.getParameter(MiaoshaUserService.COOKIE_NAME_TOKEN);
        String cookieToken=getCookieValue(request,MiaoshaUserService.COOKIE_NAME_TOKEN);
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){//如果都是空，则什么也bu做
            return "login";
        }
        //设置优先级。优先取paramToken
        String token=StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        //从redis中取出token
        return miaoshaUserService.getByToken(response,token);
    }

    private String getCookieValue(HttpServletRequest request, String cookieNameToken) {
        //遍历所有Cookie
        Cookie[] cookies = request.getCookies();
        if(cookies==null || cookies.length<=0){
            return null;
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equalsIgnoreCase(cookieNameToken)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
