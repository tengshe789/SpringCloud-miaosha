package cn.tengshe789.controller;

import cn.tengshe789.domain.MiaoshaOrder;
import cn.tengshe789.domain.MiaoshaUser;
import cn.tengshe789.domain.OrderInfo;
import cn.tengshe789.rabbitmq.MQSender;
import cn.tengshe789.rabbitmq.MiaoshaMessage;
import cn.tengshe789.redis.GoodsKey;
import cn.tengshe789.redis.MiaoshaKey;
import cn.tengshe789.redis.OrderKey;
import cn.tengshe789.redis.RedisService;
import cn.tengshe789.result.CodeMsg;
import cn.tengshe789.result.Result;
import cn.tengshe789.service.GoodsService;
import cn.tengshe789.service.MiaoshaService;
import cn.tengshe789.service.MiaoshaUserService;
import cn.tengshe789.service.OrderService;
import cn.tengshe789.vo.GoodsVo;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean {
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

    @Autowired
    MQSender sender;

    private HashMap<Long, Boolean> localOverMap =  new HashMap<Long, Boolean>();

    /**
     * 系统初始化时调用
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsList =goodsService.listGoodsVo();
        if (goodsList == null){
            return;
        }
        //将redis加载到缓存
        for (GoodsVo goods:goodsList){
            redisService.set(GoodsKey.getGoodsStock, ""+goods.getId(), goods.getStockCount());
            localOverMap.put(goods.getId(), false);
        }
    }

    @RequestMapping(value="/reset", method=RequestMethod.GET)
    public Result<Boolean> reset(Model model) {
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        for(GoodsVo goods : goodsList) {
            goods.setStockCount(10);
            redisService.set(GoodsKey.getGoodsStock, ""+goods.getId(), 10);
            localOverMap.put(goods.getId(), false);
        }
        redisService.delete(OrderKey.getMiaoshaOrderByUidGid);
        redisService.delete(MiaoshaKey.isOver);
        miaoshaService.reset(goodsList);
        return Result.success(true);
    }

    @RequestMapping(value = "/do_miaosha",method = RequestMethod.POST)
    public Result<Integer> miaosha(Model model, MiaoshaUser user,
                         @RequestParam("goodsId")long goodsId){
        model.addAttribute("user",user);
        if (user==null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        //预先在redis中减库存
        Long stock = redisService.decr(GoodsKey.getGoodsStock, "" + goodsId);
        if (stock < 0){
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        //判断是否秒杀到了，防止一个人秒杀到多个产品
        MiaoshaOrder order=orderService.getMiaoshaUserByUserIdGoodsId(user.getId(),goodsId);
        if (order!=null){
            return Result.error(CodeMsg.CHONG_FU_MIAOSHA);
        }
        //入队
        MiaoshaMessage miaoshaMessage=new MiaoshaMessage();
        miaoshaMessage.setUser(user);
        miaoshaMessage.setGoodsId(goodsId);
        sender.sendMiaoshaMessage(miaoshaMessage);
        return Result.success(0);//定义0代表排队中

    }


    //第二次的代码
//    @RequestMapping(value = "/do_miaosha",method = RequestMethod.POST)
//    @ResponseBody
//    public Result<OrderInfo> miaosha(Model model, MiaoshaUser user,
//                         @RequestParam("goodsId")long goodsId){
//        model.addAttribute("user",user);
//        if (user==null){
//            return Result.error(CodeMsg.SESSION_ERROR);
//        }
//        //判断库存
//        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
//        Integer stock = goods.getStockCount();
//        if (stock<0){
//            return Result.error(CodeMsg.MIAO_SHA_OVER);
//        }
//        //判断是否秒杀到了，防止一个人秒杀到多个产品
//        MiaoshaOrder order=orderService.getMiaoshaUserByUserIdGoodsId(user.getId(),goodsId);
//        if (order!=null){
//            return Result.error(CodeMsg.CHONG_FU_MIAOSHA);
//        }
//
//        //秒杀到了，减库存，下订单。写入秒杀订单
//        OrderInfo orderInfo=miaoshaService.miaosha(user,goods);
//        return Result.success(orderInfo);
//    }




//第一次的代码
//    @RequestMapping("/do_miaosha")
//    public String toList(Model model,MiaoshaUser user,
//                         @RequestParam("goodsId")long goodsId
//    ){
//        model.addAttribute("maioshauser",user);
//        if (user==null){
//            return "login";
//        }
//        //判断库存
//        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
//        Integer stock = goods.getStockCount();
//        if (stock<=0){
//            model.addAttribute("errmsg",CodeMsg.MIAO_SHA_OVER.getMsg());
//            return "miaosha_fail";
//        }
//        //判断是否秒杀到了，防止一个人秒杀到多个产品
//        MiaoshaOrder order=orderService.getMiaoshaUserByUserIdGoodsId(user.getId(),goodsId);
//        if (order!=null){
//            model.addAttribute("errmsg",CodeMsg.CHONG_FU_MIAOSHA.getMsg());
//            return "miaosha_fail";
//        }
//
//        //秒杀到了，减库存，下订单。写入秒杀订单
//        OrderInfo orderInfo=miaoshaService.miaosha(user,goods);
//        model.addAttribute("orderInfo",orderInfo);//将秒杀订单信息直接写入订单
//        model.addAttribute("goods",goods);//将商品信息直接写入订单
//        return "order_detail";
//    }

    /**
     * 秒杀成功：返回orderId
     * 库存不足：-1
     * 排队中：0
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/result",method = RequestMethod.GET)
    public Result<Long> miaoshaResult(Model model, MiaoshaUser user,
                                   @RequestParam("goodsId")long goodsId){
        model.addAttribute("user",user);
        if (user==null) {
        }
        long result = miaoshaService.getMiaoshaResult(user.getId(),goodsId);
        return Result.success(result);
    }
}
