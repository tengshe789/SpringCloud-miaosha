package tech.tengshe789.miaosha.sys.api.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;
import tech.tengshe789.miaosha.common.core.constants.ServiceNameConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.sys.api.feign.factories.RemoteTokenServiceFallbackFactory;

import java.util.Map;

/**
 * @program: -miaosha
 * @description: token服务接口
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
@FeignClient(value = ServiceNameConstants.AUTH_SERVICE, fallbackFactory = RemoteTokenServiceFallbackFactory.class)
public interface RemoteTokenService {
	/**
	 * 分页查询token 信息
	 *
	 * @param from   内部调用标志
	 * @param params 分页参数
	 * @param from   内部调用标志
	 * @return page
	 */
	@PostMapping("/token/page")
	Result<Page> getTokenPage(@RequestBody Map<String, Object> params, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 删除token
	 *
	 * @param from  内部调用标志
	 * @param token token
	 * @param from  内部调用标志
	 * @return
	 */
	@DeleteMapping("/token/{token}")
	Result<Boolean> removeTokenById(@PathVariable("token") String token, @RequestHeader(SecurityConstants.FROM) String from);
}
