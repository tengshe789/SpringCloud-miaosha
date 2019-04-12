package tech.tengshe789.miaosha.mall.order.biz.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.tengshe789.miaosha.common.core.constants.CodeMsgConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.common.security.annotation.Inner;
import tech.tengshe789.miaosha.mall.goods.api.feign.RemoteGoodsService;
import tech.tengshe789.miaosha.mall.goods.api.vo.GoodsVo;
import tech.tengshe789.miaosha.mall.order.api.entity.MiaoshaOrder;
import tech.tengshe789.miaosha.mall.order.api.entity.OrderInfo;
import tech.tengshe789.miaosha.mall.order.api.vo.OrderDetailVo;
import tech.tengshe789.miaosha.mall.order.biz.service.OrderService;
import tech.tengshe789.miaosha.sys.api.entity.SysUser;


import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
	private final RemoteGoodsService goodsService;

    @RequestMapping("/detail")
	@Inner
    public Result<OrderDetailVo> info(HttpServletRequest request, Model model, SysUser user,
									  @RequestParam("orderId") long orderId){
        if (user==null){
			return Result.error(CodeMsgConstants.USER_INFORMATION_IS_EMPTY);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if (order == null){
            return Result.error(CodeMsgConstants.ORDER_NOT_EXIST);
        }
        Long goodsId = order.getGoodsId();

		Result<GoodsVo> goodsVoByGoodsId = goodsService.getGoodsVoByGoodsId(goodsId);
		GoodsVo data = goodsVoByGoodsId.getData();
		OrderDetailVo detail = new OrderDetailVo().setGoods(data).setOrder(order);
        return Result.success(detail);
    }

	@PostMapping("/createOrder")
	@Inner
	public Result<OrderInfo> createOrder(SysUser user, GoodsVo goods) {
		return Result.success(orderService.createOrder(user, goods));
	}

	@GetMapping("/getMiaoshaUserByUserIdGoodsId")
	@Inner
	public Result<MiaoshaOrder> getMiaoshaUserByUserIdGoodsId(long userId, long goodsId) {
		return Result.success(orderService.getMiaoshaUserByUserIdGoodsId(userId, goodsId));
	}
}
