package upms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: -miaosha
 * @description: upms启动类
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-21 19:03
 **/
@SpringBootApplication
@Slf4j
public class MiaoshaAdminBootstap implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MiaoshaAdminBootstap.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("UPMS启动成功！");
	}
}
