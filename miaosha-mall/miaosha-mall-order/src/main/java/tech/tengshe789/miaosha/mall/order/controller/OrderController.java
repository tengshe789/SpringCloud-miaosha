package tech.tengshe789.miaosha.mall.order.controller;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.tengshe789.miaosha.common.core.constants.CodeMsgConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.mall.goods.service.GoodsService;
import tech.tengshe789.miaosha.mall.goods.vo.GoodsVo;
import tech.tengshe789.miaosha.mall.order.entity.OrderInfo;
import tech.tengshe789.miaosha.mall.order.service.OrderService;
import tech.tengshe789.miaosha.mall.order.vo.OrderDetailVo;
import tech.tengshe789.miaosha.upms.entity.SysUser;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
	private final GoodsService goodsService;

    @RequestMapping("/detail")
    public Result<OrderDetailVo> info(HttpServletRequest request, Model model, SysUser user,
									  @RequestParam("orderId") long orderId){
        if (user==null){
            return Result.error(CodeMsgConstants.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if (order == null){
            return Result.error(CodeMsgConstants.ORDER_NOT_EXIST);
        }
        Long goodsId = order.getGoodsId();

        GoodsVo goods =goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo detail=new OrderDetailVo().setGoods(goods).setOrder(order);
        return Result.success(detail);
    }
}
