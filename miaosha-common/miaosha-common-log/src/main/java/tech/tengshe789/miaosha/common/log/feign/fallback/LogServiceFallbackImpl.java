package tech.tengshe789.miaosha.common.log.feign.fallback;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.common.log.entity.SysLog;
import tech.tengshe789.miaosha.common.log.feign.LogService;

/**
 * @program: -miaosha
 * @description: 保存日志
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-13 09:09
 **/
@Slf4j
@Component
@AllArgsConstructor
public class LogServiceFallbackImpl implements LogService {
	@Setter
	private Throwable throwable;

	@Override
	public Result saveLog(SysLog sysLog, String from) {
		log.error("插入日志失败",throwable);
		return null;
	}
}
