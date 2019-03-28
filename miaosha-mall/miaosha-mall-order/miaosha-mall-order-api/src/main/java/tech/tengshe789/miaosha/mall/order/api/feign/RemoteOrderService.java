package tech.tengshe789.miaosha.mall.order.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tech.tengshe789.miaosha.common.core.constants.ServiceNameConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.mall.goods.api.feign.factories.RemoteGoodsServiceFallbackFactory;
import tech.tengshe789.miaosha.mall.goods.api.vo.GoodsVo;
import tech.tengshe789.miaosha.mall.order.api.entity.MiaoshaOrder;
import tech.tengshe789.miaosha.mall.order.api.entity.OrderInfo;
import tech.tengshe789.miaosha.sys.api.entity.SysUser;

/**
 * @program: -miaosha
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
@FeignClient(value = ServiceNameConstants.ORDER_SERVICE, fallbackFactory = RemoteGoodsServiceFallbackFactory.class)
public interface RemoteOrderService {


	@PostMapping("/order/createOrder")
	public Result<OrderInfo> createOrder(SysUser user, GoodsVo goods);

	@GetMapping("/order/getMiaoshaUserByUserIdGoodsId")
	public Result<MiaoshaOrder> getMiaoshaUserByUserIdGoodsId(long userId, long goodsId);
}
