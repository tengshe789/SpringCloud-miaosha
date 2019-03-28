package tech.tengshe789.miaosha.mall.seckill.biz.service;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.AMQP;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaosha.common.core.constants.RabbitInfo;
import tech.tengshe789.miaosha.mall.goods.api.feign.RemoteGoodsService;
import tech.tengshe789.miaosha.mall.goods.api.vo.GoodsVo;
import tech.tengshe789.miaosha.mall.order.api.entity.MiaoshaOrder;
import tech.tengshe789.miaosha.mall.order.api.feign.RemoteOrderService;
import tech.tengshe789.miaosha.mall.seckill.api.vo.MiaoshaMessage;
import tech.tengshe789.miaosha.sys.api.entity.SysUser;

/**
 * @program: -miaosha
 * @description:
 * @author: tEngSHe789
 * @create: 2019-03-28 15:14
 **/
@Slf4j
@AllArgsConstructor
@Service("miaoshaReceiver")
public class MiaoshaReceiver {
	private final RemoteGoodsService goodsService;
	private final RemoteOrderService orderService;
	private final MiaoshaService miaoshaService;

	@SneakyThrows
	@RabbitListener(queues = RabbitInfo.MIAOSHA_QUEUE)
	@RabbitHandler
	public void onMessage(String message) {
		log.info("receive message:" + RabbitInfo.MIAOSHA_QUEUE + message);
		MiaoshaMessage mm = JSONUtil.toBean(message, MiaoshaMessage.class);
		SysUser user = mm.getUser();
		long goodsId = mm.getGoodsId();

		GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId).getData();
		int stock = goods.getStockCount();
		if (stock <= 0) {
			return;
		}
		//判断是否已经秒杀到了
		MiaoshaOrder order = orderService.getMiaoshaUserByUserIdGoodsId(user.getUserId(), goodsId).getData();
		if (order != null) {
			return;
		}
		//减库存 下订单 写入秒杀订单
		miaoshaService.miaosha(user, goods);
	}
}
