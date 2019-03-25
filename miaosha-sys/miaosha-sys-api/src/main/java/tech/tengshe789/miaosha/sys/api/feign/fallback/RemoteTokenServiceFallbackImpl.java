package tech.tengshe789.miaosha.sys.api.feign.fallback;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.sys.api.feign.RemoteTokenService;

import java.util.Map;

/**
 * @author lengleng
 * feign token  fallback
 */
@Slf4j
@Component
public class RemoteTokenServiceFallbackImpl implements RemoteTokenService {
	@Setter
	private Throwable cause;

	/**
	 * 分页查询token 信息
	 *
	 * @param params 分页参数
	 * @param from   内部调用标志
	 * @return page
	 */
	@Override
	public Result getTokenPage(Map<String, Object> params, String from) {
		log.error("调用认证中心查询token 失败", cause);
		return null;
	}

	@Override
	public Result<Boolean> removeTokenById(String token, String from) {
		log.error("删除token 失败 {}", from, cause);
		return null;
	}
}
