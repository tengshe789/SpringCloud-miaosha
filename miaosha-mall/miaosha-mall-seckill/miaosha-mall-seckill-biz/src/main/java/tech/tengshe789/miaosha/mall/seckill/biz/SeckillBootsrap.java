package tech.tengshe789.miaosha.mall.seckill.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import tech.tengshe789.miaosha.common.core.annotation.EnableMiaoshaSwagger;
import tech.tengshe789.miaosha.common.core.notice.ContainerStartupNotification;
import tech.tengshe789.miaosha.common.mq.annotation.EnableMiaoshaRabbitmq;
import tech.tengshe789.miaosha.common.security.annotation.EnableMiaoshaFeignClients;
import tech.tengshe789.miaosha.common.security.annotation.EnableMiaoshaResourceServer;

/**
 * @program: -miaosha
 * @description:
 * @author: tEngSHe789
 * @create: 2019-03-28 14:07
 **/
@EnableMiaoshaRabbitmq
@SpringCloudApplication
@Slf4j
@EnableMiaoshaFeignClients
@EnableMiaoshaResourceServer
@EnableMiaoshaSwagger
public class SeckillBootsrap extends ContainerStartupNotification {
	public static void main(String[] args) {
		SpringApplication.run(SeckillBootsrap.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(">>>>>>>>>>>>>>> miaosha 平台 秒杀服务 组件 启动完成<<<<<<<<<<<<<");
	}
}
