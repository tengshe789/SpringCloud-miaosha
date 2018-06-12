package cn.tengshe789.controller;

import cn.tengshe789.domain.Goods;
import cn.tengshe789.domain.MiaoshaUser;
import cn.tengshe789.redis.RedisService;
import cn.tengshe789.service.GoodsService;
import cn.tengshe789.service.MiaoshaUserService;
import cn.tengshe789.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_list")
    public String toList(Model model,
//                         @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN,required = false)String cookieToken,
//                            //为了兼容手机端，从RequestParam中取token
//                         @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN,required = false)String paramToken
                         MiaoshaUser user){
//        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
//            return "login";
//        }
//        //设置优先级。优先取paramToken
//        String token=StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
//        //从redis中取出token
//        MiaoshaUser user=miaoshaUserService.getByToken(response,token);


        //查询商品列表
        List<GoodsVo> goodsList=goodsService.listGoodsVo();
        model.addAttribute("goodsList",goodsList);
        return "goods_list";
    }
    //商品的详情页
    @RequestMapping("/to_detail")
    public String detail(Model model,MiaoshaUser user,
                         @PathVariable("goodsId") long goodsId){
        model.addAttribute("user",user);

        GoodsVo goods=goodsService.getGoodsVoById(goodsId);
        model.addAttribute("goods",goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;

        if (now<startAt){//秒杀没开始,倒计时
            miaoshaStatus=0;
            remainSeconds=(int) ((startAt-now)/1000);

        }else if (now>endAt){//秒杀已经结束
            miaoshaStatus=2;
            remainSeconds=-1;
        }else {//秒杀进行中
            miaoshaStatus=1;
            remainSeconds=0;
        }

        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSeconds",remainSeconds);

        return "goods_detail";
    }
}
