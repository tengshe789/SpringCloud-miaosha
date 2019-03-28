package tech.tengshe789.miaosha.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import tech.tengshe789.miaosha.common.core.notice.ContainerStartupNotification;

/**
 * @program: miaosha
 * @description: 服务网关
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-07 15:59
 **/
@SpringCloudApplication
@Slf4j
public class GatewayBootstrap extends ContainerStartupNotification {
	public static void main(String[] args) {
		SpringApplication.run(GatewayBootstrap.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(">>>>>>>>>>>>>>> miaosha 平台 服务网关 组件 启动完成<<<<<<<<<<<<<");
	}
}
