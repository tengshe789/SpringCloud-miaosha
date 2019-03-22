package tech.tengshe789.miaosha.sys.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.tengshe789.miaosha.common.log.entity.SysLog;
import tech.tengshe789.miaosha.sys.api.vo.PreLogVo;

import java.util.List;

/**
 * <p>
 * 日志表 服务类
 * </p>
 * @author lengleng
 */
public interface SysLogService extends IService<SysLog> {


	/**
	 * 批量插入前端错误日志
	 *
	 * @param preLogVoList 日志信息
	 * @return true/false
	 */
	public Boolean saveBatchLogs(List<PreLogVo> preLogVoList);
}
