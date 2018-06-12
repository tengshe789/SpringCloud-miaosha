package cn.tengshe789.service;

import cn.tengshe789.dao.MiaoshaUserDao;
import cn.tengshe789.domain.MiaoshaUser;
import cn.tengshe789.exception.GlobleException;
import cn.tengshe789.redis.MiaoshaUserKey;
import cn.tengshe789.redis.RedisService;
import cn.tengshe789.result.CodeMsg;
import cn.tengshe789.util.MD5Util;
import cn.tengshe789.util.UUIDUtil;
import cn.tengshe789.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserService {

    public static final String COOKIE_NAME_TOKEN="token";
    @Autowired
    MiaoshaUserDao miaoshaUserDao;
    @Autowired
    RedisService redisService;

    public MiaoshaUser getById(long id){
        return miaoshaUserDao.getById(id);
    }

    public MiaoshaUser getByToken(HttpServletResponse response,String token) {
        if (StringUtils.isEmpty(token)){
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //延长有效期.如果用户不是第一次登陆，则重新生成一次cookie以延长cookie的有效期
        if (user!=null){addCookie(response,token,user);}
        return user;
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo==null){
            throw  new GlobleException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if(user==null){
            throw  new GlobleException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if(!calcPass.equalsIgnoreCase(dbPass)){
            throw new GlobleException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token	 = UUIDUtil.uuid();
        addCookie(response,token,user);
        return true;
    }
    //生成cookie
    private void addCookie(HttpServletResponse response, String token,MiaoshaUser user){

        redisService.set(MiaoshaUserKey.token,token,user);
        Cookie cookie=new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());//cookie有效期=userkey的有效期
        cookie.setPath("/");//设置成网站根目录
        response.addCookie(cookie);
    }

}
