package tech.tengshe789.miaosha.mall.order.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import tech.tengshe789.miaosha.common.core.annotation.EnableMiaoshaSwagger;
import tech.tengshe789.miaosha.common.core.notice.ContainerStartupNotification;
import tech.tengshe789.miaosha.common.security.annotation.EnableMiaoshaFeignClients;
import tech.tengshe789.miaosha.common.security.annotation.EnableMiaoshaResourceServer;

/**
 * @program: -miaosha
 * @author: tEngSHe789
 * @create: 2019-03-28
 **/
@SpringCloudApplication
@Slf4j
@EnableMiaoshaFeignClients
@EnableMiaoshaResourceServer
@EnableMiaoshaSwagger
public class OrderBootstrap extends ContainerStartupNotification {
	@Override
	public void run(String... args) throws Exception {
		log.info(">>>>>>>>>>>>>>> miaosha 平台 订单服务 组件 启动完成<<<<<<<<<<<<<");
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderBootstrap.class,args);
	}
}
