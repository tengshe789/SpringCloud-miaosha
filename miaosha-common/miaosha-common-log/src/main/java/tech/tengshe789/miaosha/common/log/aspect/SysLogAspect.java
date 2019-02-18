package tech.tengshe789.miaosha.common.log.aspect;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import tech.tengshe789.miaosha.common.log.annotation.SysLog;
import tech.tengshe789.miaosha.common.log.event.SysLogEvent;
import tech.tengshe789.miaosha.common.log.utils.SysLogUtils;

import java.lang.annotation.Annotation;

/**
 * @program: -miaosha
 * @description: 操作日志使用spring event异步入库
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-12 15:44
 **/
@Slf4j
@Aspect
@AllArgsConstructor
public class SysLogAspect {
	/**
	 * spring事件使用步骤如下:
	 *
	 * 1.先自定义事件：事件需要继承 ApplicationEvent
	 *
	 * 2.定义事件监听器: 需要实现 ApplicationListener
	 *
	 * 3.使用容器对事件进行发布
	 */
	private final ApplicationEventPublisher publisher;

	@SneakyThrows
	@Around("@annotation(sysLog)")
	public Object around (ProceedingJoinPoint point, SysLog sysLog) {
		String className = point.getTarget().getClass().getName();
		String methodName = point.getSignature().getName();
		log.debug("[类名]:{},[方法]:{}", className, methodName);

		tech.tengshe789.miaosha.common.log.entity.SysLog log = SysLogUtils.getSysLog();
		log.setTitle(sysLog.value());
		Long startTime = System.currentTimeMillis();
		Object obj = point.proceed();
		Long endTime = System.currentTimeMillis();
		log.setTime(endTime - startTime);
		//事件入队
		publisher.publishEvent(new SysLogEvent(obj,log));
		return obj;
	}
}
