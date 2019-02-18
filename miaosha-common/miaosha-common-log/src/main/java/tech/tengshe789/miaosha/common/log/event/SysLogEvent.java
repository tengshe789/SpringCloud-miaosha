package tech.tengshe789.miaosha.common.log.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import tech.tengshe789.miaosha.common.log.entity.SysLog;

/**
 * @program: -miaosha
 * @description: SysLog事件
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-12 17:04
 **/
@Getter
public class SysLogEvent extends ApplicationEvent {
	private SysLog sysLog;

	public SysLogEvent(Object source,SysLog sysLog) {
		super(source);
		this.sysLog = sysLog;
	}
}
