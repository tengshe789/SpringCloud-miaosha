package tech.tengshe789.miaosha.common.log.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;
import tech.tengshe789.miaosha.common.core.constants.ServiceNameConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.common.log.entity.SysLog;

/**
 * @program: -miaosha
 * @description: 保存日志
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-12 17:40
 **/
@FeignClient(value = ServiceNameConstants.LOG_SERVICE)
public interface LogService {

	/**
	 * 保存日志
	 *
	 * @param sysLog 日志实体
	 * @param from   是否内部调用
	 * @return succes、false
	 */
	@PostMapping("/log/save")
	public Result saveLog (@RequestBody SysLog sysLog,
						   @RequestHeader(SecurityConstants.FROM) String from);
}
