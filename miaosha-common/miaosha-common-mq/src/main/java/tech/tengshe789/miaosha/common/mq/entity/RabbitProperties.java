package tech.tengshe789.miaosha.common.mq.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: -miaosha
 * @author: tEngSHe789
 * @create: 2019-03-28 13:55
 **/
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
@Accessors(chain = true)
public class RabbitProperties {
	String host;

	String port;

	String username;

	String password;
}
