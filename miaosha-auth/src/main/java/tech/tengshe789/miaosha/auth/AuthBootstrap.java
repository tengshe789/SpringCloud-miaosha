package tech.tengshe789.miaosha.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import tech.tengshe789.miaosha.common.core.notice.ContainerStartupNotification;
import tech.tengshe789.miaosha.common.security.annotation.EnableMiaoshaFeignClients;

/**
 * @program: -miaosha
 * @description: 鉴权中心入口
 * @author: tEngSHe789
 * @create: 2019-03-21 10:09
 **/
@SpringCloudApplication
@Slf4j
@EnableMiaoshaFeignClients
public class AuthBootstrap extends ContainerStartupNotification {

	public static void main(String[] args) {
		SpringApplication.run(AuthBootstrap.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(">>>>>>>>>>>>>>> miaosha 平台 鉴权中心 组件 启动完成<<<<<<<<<<<<<");
	}
}
