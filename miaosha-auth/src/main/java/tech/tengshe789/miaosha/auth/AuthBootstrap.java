package tech.tengshe789.miaosha.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.tengshe789.miaosha.common.core.notice.ContainerStartupNotification;
import tech.tengshe789.miaosha.common.security.annotation.EnableMiaoshaFeignClients;

/**
 * @program: -miaosha
 * @description: 鉴权中心入口
 * @author: tEngSHe789
 * @create: 2019-03-21 10:09
 **/
@SpringBootApplication
@Slf4j
@EnableMiaoshaFeignClients
public class AuthBootstrap extends ContainerStartupNotification {

	public static void main(String[] args) {
		SpringApplication.run(AuthBootstrap.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Miaosha鉴权中心启动SUccess");
	}
}
