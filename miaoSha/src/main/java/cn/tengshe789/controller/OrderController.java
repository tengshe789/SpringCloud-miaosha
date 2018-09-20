package cn.tengshe789.controller;

import cn.tengshe789.domain.MiaoshaUser;
import cn.tengshe789.domain.OrderInfo;
import cn.tengshe789.result.CodeMsg;
import cn.tengshe789.result.Result;
import cn.tengshe789.service.GoodsService;
import cn.tengshe789.service.OrderService;
import cn.tengshe789.vo.GoodsVo;
import cn.tengshe789.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/detail")
    public Result<OrderDetailVo> info(Model model, MiaoshaUser user,
                                      @RequestParam("orderId") long orderId){
        if (user==null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if (order == null){
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        Long goodsId = order.getGoodsId();
//        if (goodsId==null){//断点
//            return Result.error(CodeMsg.ORDER_GOODSID_NULL);
//        }
        GoodsVo goods =goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo detail=new OrderDetailVo();
        detail.setGoods(goods);
        detail.setOrder(order);
        return Result.success(detail);
    }
}
