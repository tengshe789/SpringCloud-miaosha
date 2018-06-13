package cn.tengshe789.controller;

import cn.tengshe789.domain.MiaoshaOrder;
import cn.tengshe789.domain.MiaoshaUser;
import cn.tengshe789.domain.OrderInfo;
import cn.tengshe789.redis.RedisService;
import cn.tengshe789.result.CodeMsg;
import cn.tengshe789.service.GoodsService;
import cn.tengshe789.service.MiaoshaService;
import cn.tengshe789.service.MiaoshaUserService;
import cn.tengshe789.service.OrderService;
import cn.tengshe789.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {
    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/do_miaosha")
    public String toList(Model model,MiaoshaUser user,
    @RequestParam("goodsId")long goodsId
    ){
        model.addAttribute("maioshauser",user);
        if (user==null){
            return "login";
        }
        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        Integer stock = goods.getStockCount();
        if (stock<=0){
            model.addAttribute("errmsg",CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha-fail";
        }
        //判断是否秒杀到了，防止一个人秒杀到多个产品
        MiaoshaOrder order=orderService.getMiaoshaUserByUserIdGoodsId(user.getId(),goodsId);
        if (order!=null){
            model.addAttribute("errmsg",CodeMsg.CHONG_FU_MIAOSHA.getMsg());
            return "miaosha-fail";
        }

        //秒杀到了，减库存，下订单。写入秒杀订单
        OrderInfo orderInfo=miaoshaService.miaosha(user,goods);
        model.addAttribute("orderInfo",orderInfo);//将秒杀订单信息直接写入订单
        model.addAttribute("goods",goods);//将商品信息直接写入订单
        return "order_detail";
    }

}
