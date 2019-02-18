package tech.tengshe789.miaosha.upms;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tech.tengshe789.miaosha.common.core.notice.ContainerStartupNotification;

/**
 * @program: -miaosha
 * @description: upms启动类
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-21 19:03
 **/
@SpringBootApplication
@Slf4j
@MapperScan("tech.tengshe789.miaosha.upms.mapper")
@EnableCaching
public class MiaoshaUpmsBootstap extends ContainerStartupNotification {

	public static void main(String[] args) {
		SpringApplication.run(MiaoshaUpmsBootstap.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("UPMS启动成功！");
	}
}
