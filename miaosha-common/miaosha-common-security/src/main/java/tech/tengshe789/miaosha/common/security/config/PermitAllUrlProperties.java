package tech.tengshe789.miaosha.common.security.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: -miaosha
 * @description: 资源服务器对外直接暴露URL
 * @author: tEngSHe789
 * @create: 2019-03-31 22:20
 **/
@Data
@Configuration
@RefreshScope
@ConditionalOnExpression("!'${security.oauth2.client.ignore-urls}'.isEmpty()")
@ConfigurationProperties(prefix = "security.oauth2.client")
public class PermitAllUrlProperties {
	private List<String> ignoreUrls = new ArrayList<>();
}
