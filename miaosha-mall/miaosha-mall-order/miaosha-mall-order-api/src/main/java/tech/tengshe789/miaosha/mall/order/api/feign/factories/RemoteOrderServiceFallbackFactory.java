package tech.tengshe789.miaosha.mall.order.api.feign.factories;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaosha.mall.order.api.feign.RemoteOrderService;
import tech.tengshe789.miaosha.mall.order.api.feign.fallback.RemoteOrderServiceFallbackImpl;

/**
 * @program: -miaosha
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
@Component
public class RemoteOrderServiceFallbackFactory implements FallbackFactory<RemoteOrderService> {

	@Override
	public RemoteOrderService create(Throwable throwable) {
		RemoteOrderServiceFallbackImpl remoteOrderServiceFallback = new RemoteOrderServiceFallbackImpl();
		remoteOrderServiceFallback.setCause(throwable);
		return remoteOrderServiceFallback;
	}
}
