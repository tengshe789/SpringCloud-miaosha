package cn.tengshe789.controller;

import cn.tengshe789.redis.RedisService;
import cn.tengshe789.result.CodeMsg;
import cn.tengshe789.result.Result;
import cn.tengshe789.service.MiaoshaUserService;
import cn.tengshe789.util.ValidatorUtil;
import cn.tengshe789.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    //日志
    private static Logger logger=LoggerFactory.getLogger(LoginController.class);
    @Autowired
    RedisService redisService;

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo){
        logger.info(loginVo.toString());
//        //参数校验
//        String passInput =loginVo.getPassword();
//        String moblie=loginVo.getMobile();
//        if(StringUtils.isEmpty(passInput)){
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }
//        if (StringUtils.isEmpty(moblie)){
//            return Result.error(CodeMsg.MOBILE_EMPTY);
//        }
//        if (ValidatorUtil.isMoblie(moblie)){
//            return Result.error(CodeMsg.MOBILE_ERROR);
//        }

        //登陆
//        CodeMsg cm = miaoshaUserService.login(loginVo);
//        if (cm.getCode()==0){
//            return Result.success(true);
//        }else {
//            return Result.error(cm);
//        }
        miaoshaUserService.login(response,loginVo);
        return Result.success(true);
    }
}
