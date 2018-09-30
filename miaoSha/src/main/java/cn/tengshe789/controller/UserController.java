package cn.tengshe789.controller;

import cn.tengshe789.dao.MiaoshaUserDao;
import cn.tengshe789.domain.MiaoshaUser;
import cn.tengshe789.result.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@Api(value="测试接口Controller")
public class UserController {


    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model,MiaoshaUser user){
        return Result.success(user);
    }
}
