package tech.tengshe789.miaosha.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaosha.common.core.constants.StatusConstants;
import tech.tengshe789.miaosha.common.log.entity.SysLog;
import tech.tengshe789.miaosha.upms.mapper.SysLogMapper;
import tech.tengshe789.miaosha.upms.service.SysLogService;
import tech.tengshe789.miaosha.upms.vo.PreLogVo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

	/**
	 * 批量插入前端错误日志
	 *
	 * @param preLogVoList 日志信息
	 * @return true/false
	 */
	@Override
	public Boolean saveBatchLogs(List<PreLogVo> preLogVoList) {
		List<SysLog> sysLogs = preLogVoList.stream()
			.map(pre -> {
				return new SysLog()
					.setType(StatusConstants.STATUS_LOCK)
					.setTitle(pre.getInfo())
					.setException(pre.getStack())
					.setParams(pre.getMessage())
					.setCreateTime(LocalDateTime.now())
					.setRequestUri(pre.getUrl())
					.setCreateBy(pre.getUser());
			})
			.collect(Collectors.toList());
		return this.saveBatch(sysLogs);
	}
}
