package tech.tengshe789.miaosha.mall.goods.biz;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import tech.tengshe789.miaosha.common.core.annotation.EnableMiaoshaSwagger;
import tech.tengshe789.miaosha.common.core.notice.ContainerStartupNotification;
import tech.tengshe789.miaosha.common.security.annotation.EnableMiaoshaFeignClients;
import tech.tengshe789.miaosha.common.security.annotation.EnableMiaoshaResourceServer;

/**
 * @program: -miaosha
 * @description:
 * @author: tEngSHe789
 * @create: 2019-03-20 15:32
 **/
@SpringCloudApplication
@Slf4j
@EnableMiaoshaFeignClients
@EnableMiaoshaResourceServer
@EnableMiaoshaSwagger
@MapperScan("tech.tengshe789.miaosha.mall.goods.biz.dao")
public class GoodsBootstrap extends ContainerStartupNotification {
	@Override
	public void run(String... args) throws Exception {
		log.info(">>>>>>>>>>>>>>> miaosha 平台 商品服务 组件 启动完成<<<<<<<<<<<<<");
	}

	public static void main(String[] args) {
		SpringApplication.run(GoodsBootstrap.class,args);
	}
}

