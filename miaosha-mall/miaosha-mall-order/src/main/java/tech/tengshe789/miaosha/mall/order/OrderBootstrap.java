package tech.tengshe789.miaosha.mall.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.tengshe789.miaosha.common.core.notice.ContainerStartupNotification;
import tech.tengshe789.miaosha.common.security.annotation.EnableMiaoshaFeignClients;

/**
 * @program: -miaosha
 * @description:
 * @author: tEngSHe789
 * @create: 2019-03-20 15:28
 **/
@SpringBootApplication
@Slf4j
@EnableMiaoshaFeignClients
public class OrderBootstrap extends ContainerStartupNotification {
	@Override
	public void run(String... args) throws Exception {
		log.info("ORDER启动成功！");
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderBootstrap.class,args);
	}
}
