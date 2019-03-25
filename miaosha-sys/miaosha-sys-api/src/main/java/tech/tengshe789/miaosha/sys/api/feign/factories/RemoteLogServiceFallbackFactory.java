package tech.tengshe789.miaosha.sys.api.feign.factories;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaosha.sys.api.feign.RemoteLogService;
import tech.tengshe789.miaosha.sys.api.feign.fallback.RemoteLogServiceFallbackImpl;

/**
 * @author lengleng
 */
@Component
public class RemoteLogServiceFallbackFactory implements FallbackFactory<RemoteLogService> {

	@Override
	public RemoteLogService create(Throwable throwable) {
		RemoteLogServiceFallbackImpl remoteLogServiceFallback = new RemoteLogServiceFallbackImpl();
		remoteLogServiceFallback.setCause(throwable);
		return remoteLogServiceFallback;
	}
}
