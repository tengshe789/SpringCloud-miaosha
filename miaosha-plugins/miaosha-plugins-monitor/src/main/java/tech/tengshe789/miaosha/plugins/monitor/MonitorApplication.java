package tech.tengshe789.miaosha.plugins.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import tech.tengshe789.miaosha.common.core.notice.ContainerStartupNotification;

/**
 * @program: miaosha
 * @description: admin-ui 启动入口
 * @author: tEngSHe789
 * @create: 2019-03-04 17:14
 **/
@EnableTurbine
@EnableAdminServer
@EnableHystrixDashboard
@SpringCloudApplication
@Slf4j
public class MonitorApplication extends ContainerStartupNotification {

	@Override
	public void run(String... args) throws Exception {
		log.info(">>>>>>>>>>>>>>> miaosha 平台 监控服务 组件 启动完成<<<<<<<<<<<<<");
	}

	public static void main(String[] args) {
		SpringApplication.run(MonitorApplication.class, args);
	}
}
