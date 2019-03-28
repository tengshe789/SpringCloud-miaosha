package tech.tengshe789.miaosha.sys.biz;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import tech.tengshe789.miaosha.common.core.annotation.EnableMiaoshaSwagger;
import tech.tengshe789.miaosha.common.core.notice.ContainerStartupNotification;
import tech.tengshe789.miaosha.common.security.annotation.EnableMiaoshaFeignClients;
import tech.tengshe789.miaosha.common.security.annotation.EnableMiaoshaResourceServer;

/**
 * @program: -miaosha
 * @description: sys启动类
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-21 19:03
 **/
@EnableMiaoshaSwagger
@SpringCloudApplication
@Slf4j
@MapperScan("tech.tengshe789.miaosha.sys.biz.mapper")
@EnableMiaoshaFeignClients
@EnableMiaoshaResourceServer
public class MiaoshaSysBootstap extends ContainerStartupNotification {

	public static void main(String[] args) {
		SpringApplication.run(MiaoshaSysBootstap.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(">>>>>>>>>>>>>>> miaosha 平台 系统服务 组件 启动完成<<<<<<<<<<<<<");
	}
}
