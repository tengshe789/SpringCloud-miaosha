package tech.tengshe789.miaosha.common.log.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;
import tech.tengshe789.miaosha.common.log.feign.LogService;

/**
 * @program: -miaosha
 * @description: 异步监听日志事件
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-12 17:01
 **/
@Slf4j
@AllArgsConstructor
public class SysLogListener {
	@Autowired
	private LogService logService;

	@Async
	@EventListener(SysLogEvent.class)
	@Order
	public void saveSysLog (SysLogEvent event) {
		logService.saveLog(event.getSysLog(), SecurityConstants.FROM);
	}
}
