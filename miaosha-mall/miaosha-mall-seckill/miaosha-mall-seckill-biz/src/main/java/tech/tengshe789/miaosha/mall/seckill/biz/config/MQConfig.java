package tech.tengshe789.miaosha.mall.seckill.biz.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.tengshe789.miaosha.common.core.constants.RabbitInfo;

/**
 * @program: -miaosha
 * @author: tEngSHe789
 * @create: 2019-03-30 22:05
 **/
@Configuration
public class MQConfig {

	@Bean
	public Queue miaoShaQueue() {
		return new Queue(RabbitInfo.MIAOSHA_QUEUE, true);
	}
}

