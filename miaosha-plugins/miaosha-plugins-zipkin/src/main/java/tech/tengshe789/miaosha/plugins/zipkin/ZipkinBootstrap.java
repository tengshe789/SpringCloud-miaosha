package tech.tengshe789.miaosha.plugins.zipkin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import tech.tengshe789.miaosha.common.core.notice.ContainerStartupNotification;
import zipkin.storage.mysql.MySQLStorage;
import zipkin2.server.internal.EnableZipkinServer;

import javax.sql.DataSource;

/**
 * @program: -miaosha
 * @author: tEngSHe789
 * @create: 2019-03-28 16:24
 **/
@EnableZipkinServer
@SpringCloudApplication
@Slf4j
public class ZipkinBootstrap extends ContainerStartupNotification {

	@Override
	public void run(String... args) throws Exception {
		log.info(">>>>>>>>>>>>>>> miaosha 平台 zipkin服务 组件 启动完成<<<<<<<<<<<<<");
	}

	public static void main(String[] args) {
		SpringApplication.run(ZipkinBootstrap.class, args);
	}

	@Bean
	public MySQLStorage mySQLStorage(DataSource datasource) {
		return MySQLStorage.builder().datasource(datasource).executor(Runnable::run).build();
	}

}
