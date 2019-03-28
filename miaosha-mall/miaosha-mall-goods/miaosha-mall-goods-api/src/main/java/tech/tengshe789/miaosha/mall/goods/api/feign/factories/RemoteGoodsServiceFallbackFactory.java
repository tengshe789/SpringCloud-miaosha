package tech.tengshe789.miaosha.mall.goods.api.feign.factories;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaosha.mall.goods.api.feign.RemoteGoodsService;
import tech.tengshe789.miaosha.mall.goods.api.feign.fallback.RemoteGoodsServiceFallbackImpl;

/**
 * @program: -miaosha
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
@Component
public class RemoteGoodsServiceFallbackFactory implements FallbackFactory<RemoteGoodsService> {

	@Override
	public RemoteGoodsService create(Throwable throwable) {
		RemoteGoodsServiceFallbackImpl remoteGoodsServiceFallback = new RemoteGoodsServiceFallbackImpl();
		remoteGoodsServiceFallback.setCause(throwable);
		return remoteGoodsServiceFallback;
	}
}
