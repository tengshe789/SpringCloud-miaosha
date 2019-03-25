package tech.tengshe789.miaosha.sys.api.feign.factories;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaosha.sys.api.feign.RemoteUserService;
import tech.tengshe789.miaosha.sys.api.feign.fallback.RemoteUserServiceFallbackImpl;

/**
 * @author lengleng
 */
@Component
public class RemoteUserServiceFallbackFactory implements FallbackFactory<RemoteUserService> {

	@Override
	public RemoteUserService create(Throwable throwable) {
		RemoteUserServiceFallbackImpl remoteUserServiceFallback = new RemoteUserServiceFallbackImpl();
		remoteUserServiceFallback.setCause(throwable);
		return remoteUserServiceFallback;
	}
}
