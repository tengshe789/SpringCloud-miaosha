package upms.vo;


import lombok.Data;
import upms.entity.SysLog;

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
