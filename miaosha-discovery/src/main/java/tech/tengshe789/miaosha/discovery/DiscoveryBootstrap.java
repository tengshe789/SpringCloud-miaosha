package tech.tengshe789.miaosha.discovery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tech.tengshe789.miaosha.common.core.notice.ContainerStartupNotification;

/**
 * @program: miaosha
 * @description: consul服务端（生产端）启动入口
 * @doc: https://learn.hashicorp.com/consul/getting-started/install.html?spm=a2c4e.11153940.blogcont334533.19.4af025aeejeZGv
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-07 10:43
 **/
@Slf4j
@SpringCloudApplication
public class DiscoveryBootstrap extends ContainerStartupNotification {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryBootstrap.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(">>>>>>>>>>>>>>> miaosha 程序 服务注册功能 启动完成<<<<<<<<<<<<<");
	}
}
