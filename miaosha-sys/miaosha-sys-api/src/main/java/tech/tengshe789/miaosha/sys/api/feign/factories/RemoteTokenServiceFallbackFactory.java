package tech.tengshe789.miaosha.sys.api.feign.factories;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaosha.sys.api.feign.RemoteTokenService;
import tech.tengshe789.miaosha.sys.api.feign.fallback.RemoteTokenServiceFallbackImpl;

/**
 * @author lengleng
 */
@Component
public class RemoteTokenServiceFallbackFactory implements FallbackFactory<RemoteTokenService> {

	@Override
	public RemoteTokenService create(Throwable throwable) {
		RemoteTokenServiceFallbackImpl remoteTokenServiceFallback = new RemoteTokenServiceFallbackImpl();
		remoteTokenServiceFallback.setCause(throwable);
		return remoteTokenServiceFallback;
	}
}
