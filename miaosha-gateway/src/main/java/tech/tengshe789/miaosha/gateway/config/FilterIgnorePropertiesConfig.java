package tech.tengshe789.miaosha.gateway.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: -miaosha
 * @description: 网关文件配置不需要鉴权的client
 * @author: tEngSHe789
 * @create: 2019-03-25 09:50
 **/
@Data
@Configuration
//动态刷新
@RefreshScope
// 在ignore 是空的情况下使用相关配置或者实例化bean
@ConditionalOnExpression("!'${ignore}'.isEmpty()")
@ConfigurationProperties(prefix = "ignore")
public class FilterIgnorePropertiesConfig {
	private List<String> clients = new ArrayList<>();
	private List<String> swaggerProviders=new ArrayList<>();
}
