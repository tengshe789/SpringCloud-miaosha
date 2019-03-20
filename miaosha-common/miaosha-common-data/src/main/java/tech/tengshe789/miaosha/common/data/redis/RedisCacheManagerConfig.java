package tech.tengshe789.miaosha.common.data.redis;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * CacheManagerCustomizers配置
 *
 * @author L.cm
 */
@Configuration
@ConditionalOnMissingBean(CacheManagerCustomizers.class)
public class RedisCacheManagerConfig {

	@Bean
	public CacheManagerCustomizers cacheManagerCustomizers(
		ObjectProvider<List<CacheManagerCustomizer<?>>> customizers) {
		return new CacheManagerCustomizers(customizers.getIfAvailable());
	}
}
