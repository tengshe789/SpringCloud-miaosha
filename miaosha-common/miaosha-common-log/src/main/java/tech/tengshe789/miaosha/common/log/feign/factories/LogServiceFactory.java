package tech.tengshe789.miaosha.common.log.feign.factories;

import feign.hystrix.FallbackFactory;
import tech.tengshe789.miaosha.common.log.feign.fallback.LogServiceFallbackImpl;

/**
 * @program: -miaosha
 * @description:
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-13 09:15
 **/
public class LogServiceFactory implements FallbackFactory<LogServiceFallbackImpl> {

	@Override
	public LogServiceFallbackImpl create(Throwable throwable) {
		return new LogServiceFallbackImpl(throwable);
	}

}
