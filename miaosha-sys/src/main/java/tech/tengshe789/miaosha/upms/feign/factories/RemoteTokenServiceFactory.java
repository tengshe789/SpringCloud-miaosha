package tech.tengshe789.miaosha.upms.feign.factories;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaosha.upms.feign.RemoteTokenService;
import tech.tengshe789.miaosha.upms.feign.fallback.RemoteTokenServiceImpl;

/**
 * @program: -miaosha
 * @description:
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-13 11:14
 **/
@Component
@Slf4j
public class RemoteTokenServiceFactory implements FallbackFactory<RemoteTokenService> {


	@Override
	public RemoteTokenService create(Throwable throwable) {
		RemoteTokenServiceImpl remoteTokenService = new RemoteTokenServiceImpl();
		remoteTokenService.setThrowable(throwable);
		return remoteTokenService;
	}
}
