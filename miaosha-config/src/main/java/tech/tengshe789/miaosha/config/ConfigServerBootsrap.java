package tech.tengshe789.miaosha.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @program: -miaosha
 * @description: miaosha配置中心
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-21 18:54
 **/
@SpringCloudApplication
@Slf4j
@EnableConfigServer
public class ConfigServerBootsrap implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerBootsrap.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("miaosha配置中心启动成功");
	}
}
