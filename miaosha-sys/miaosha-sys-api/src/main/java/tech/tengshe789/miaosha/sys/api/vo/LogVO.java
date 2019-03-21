package tech.tengshe789.miaosha.sys.api.vo;


import lombok.Data;
import tech.tengshe789.miaosha.common.log.entity.SysLog;

import java.io.Serializable;

/**
 * @author lengleng
 */
@Data
public class LogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private SysLog sysLog;
	private String username;
}
