package tech.tengshe789.miaosha.common.security.service;

import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;

import javax.sql.DataSource;

/**
 * @program: -miaosha
 * @description:
 * @author: tEngSHe789
 * @create: 2019-03-21 15:31
 **/
public class MiaoshaClientDetailsService extends JdbcClientDetailsService {

	public MiaoshaClientDetailsService(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 重写原生方法支持redis缓存
	 *
	 * @param clientId
	 * @return
	 * @throws InvalidClientException
	 */
	@Override
	@SneakyThrows
	@Cacheable(value = SecurityConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
	public ClientDetails loadClientByClientId(String clientId) {
		return super.loadClientByClientId(clientId);
	}
}

