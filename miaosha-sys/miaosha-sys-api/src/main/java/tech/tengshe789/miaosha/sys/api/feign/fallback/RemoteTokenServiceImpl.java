package tech.tengshe789.miaosha.sys.api.feign.fallback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.sys.api.feign.RemoteTokenService;

import java.util.Map;

/**
 * @program: -miaosha
 * @description:
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-13 10:26
 **/
@Slf4j
@Component
public class RemoteTokenServiceImpl implements RemoteTokenService {
	@Setter
	private Throwable throwable;

	@Override
	public Result<Page> getTokenPage(Map<String, Object> params, String from) {
		log.error("调用认证中心查询token 失败", throwable);
		return null;
	}

	@Override
	public Result<Boolean> removeTokenById(String token, String from) {
		log.error("删除token 失败 {}", token, throwable);
		return null;
	}
}
