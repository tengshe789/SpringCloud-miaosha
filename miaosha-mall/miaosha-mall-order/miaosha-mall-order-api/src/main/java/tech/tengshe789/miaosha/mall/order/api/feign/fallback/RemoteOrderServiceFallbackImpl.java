package tech.tengshe789.miaosha.mall.order.api.feign.fallback;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.mall.goods.api.vo.GoodsVo;
import tech.tengshe789.miaosha.mall.order.api.entity.MiaoshaOrder;
import tech.tengshe789.miaosha.mall.order.api.entity.OrderInfo;
import tech.tengshe789.miaosha.mall.order.api.feign.RemoteOrderService;
import tech.tengshe789.miaosha.sys.api.entity.SysUser;

/**
 * @program: -miaosha
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
@Slf4j
@Component
public class RemoteOrderServiceFallbackImpl implements RemoteOrderService {
	@Setter
	private Throwable cause;

	@Override
	@PostMapping("/order/createOrder")
	public Result<OrderInfo> createOrder(SysUser user, GoodsVo goods) {
		log.error("feign 插入日志失败", cause);
		return null;
	}

	@Override
	@GetMapping("/order/getMiaoshaUserByUserIdGoodsId")
	public Result<MiaoshaOrder> getMiaoshaUserByUserIdGoodsId(long userId, long goodsId) {
		log.error("feign 插入日志失败", cause);
		return null;
	}
}
