package tech.tengshe789.miaosha.mall.seckill.biz.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import tech.tengshe789.miaosha.common.core.constants.CommonConstants;
import tech.tengshe789.miaosha.common.security.exception.SystemException;
import tech.tengshe789.miaosha.mall.goods.api.feign.RemoteGoodsService;
import tech.tengshe789.miaosha.mall.goods.api.vo.GoodsVo;
import tech.tengshe789.miaosha.mall.order.api.feign.RemoteOrderService;

import java.util.HashMap;
import java.util.List;

/**
 * @program: -miaosha
 * @description:
 * @author: tEngSHe789
 * @create: 2019-03-30 21:51
 **/
public class LoadDataIntoCache implements InitializingBean {
	@Autowired
	private RemoteGoodsService goodsService;
	@Autowired
	private RedisTemplate redisTemplate;

	public static HashMap<Long, Boolean> localOverMap = new HashMap<Long, Boolean>(16);

	/**
	 * 系统初始化时调用
	 *
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		List<GoodsVo> goodsList = goodsService.listGoodsVo().getData();
		if (goodsList == null) {
			throw new SystemException();
		}
		//将redis加载到缓存
		for (GoodsVo goods : goodsList) {
			redisTemplate.opsForValue().set(CommonConstants.GOODS_STOCK_KEY + goods.getId(), goods.getStockCount());
			localOverMap.put(goods.getId(), false);
		}
	}
}
