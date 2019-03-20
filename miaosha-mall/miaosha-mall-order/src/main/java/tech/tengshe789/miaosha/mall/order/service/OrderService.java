package tech.tengshe789.miaosha.mall.order.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.tengshe789.miaosha.common.core.constants.CommonConstants;
import tech.tengshe789.miaosha.mall.goods.vo.GoodsVo;
import tech.tengshe789.miaosha.mall.order.dao.OrderDao;
import tech.tengshe789.miaosha.mall.order.entity.MiaoshaOrder;
import tech.tengshe789.miaosha.mall.order.entity.OrderInfo;
import tech.tengshe789.miaosha.upms.entity.SysUser;

import java.util.Date;

@Service("orderService")
@AllArgsConstructor
public class OrderService {
	private final OrderDao orderDao;
	private final RedisTemplate redisTemplate;
	private final CacheManager cacheManager;

	/**
	 * 获取miaosha订单信息
	 * @param userId
	 * @param goodsId
	 * @return MiaoshaOrder
	 */
    public MiaoshaOrder getMiaoshaUserByUserIdGoodsId(long userId, long goodsId) {
//        return  orderDao.getMiaoshaOrderByUserIdGoodsId(userId,goodsId);
		Object o = redisTemplate.opsForValue().get(CommonConstants.MIAOSHA_ORDER_KEY + userId + "_" + goodsId);
		return (MiaoshaOrder) o;
    }

	/**
	 * 通过orderId获取order详情
	 * @param orderId
	 * @return OrderInfo
	 */
	public OrderInfo getOrderById(long orderId) {
        return orderDao.getOrderById(orderId);
    }

	@Transactional(rollbackFor = Exception.class)
	public OrderInfo createOrder(SysUser user, GoodsVo goods) {
        OrderInfo orderInfo =
			new OrderInfo().setCreateDate(new Date())
							.setDeliveryAddrId(0L)
							.setGoodsCount(1)
							.setGoodsId(goods.getId())
							.setGoodsName(goods.getGoodsName())
							.setGoodsPrice(goods.getMiaoshaPrice())
							.setOrderChannel(1)
							.setStatus(0)
							.setUserId(user.getUserId());

        orderDao.insert(orderInfo);
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder()
									.setGoodsId(goods.getId())
									.setOrderId(orderInfo.getId())
									.setUserId(user.getUserId());
		orderDao.insertMiaoshaOrder(miaoshaOrder);
        //秒杀成功后把信息写到缓存
        redisTemplate.opsForValue().set(CommonConstants.MIAOSHA_ORDER_KEY+user
                .getUserId()+"_"+goods.getId(),miaoshaOrder);
        return orderInfo;
    }

	/**
	 * 删库走人
	 */
	@CacheEvict(value = "user_details", key = "#sysUser.username")
	public Boolean deleteAllOrders() {
        orderDao.deleteAllOrders();
        orderDao.deleteAllMiaoshaOrders();
        return Boolean.TRUE;
    }

}
