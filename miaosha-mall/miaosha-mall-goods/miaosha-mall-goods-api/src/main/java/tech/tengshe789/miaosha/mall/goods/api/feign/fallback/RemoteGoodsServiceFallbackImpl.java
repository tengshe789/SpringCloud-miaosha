package tech.tengshe789.miaosha.mall.goods.api.feign.fallback;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.common.log.entity.SysLog;
import tech.tengshe789.miaosha.mall.goods.api.feign.RemoteGoodsService;
import tech.tengshe789.miaosha.mall.goods.api.vo.GoodsVo;

import java.util.List;

/**
 * @program: -miaosha
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
@Slf4j
@Component
public class RemoteGoodsServiceFallbackImpl implements RemoteGoodsService {
	@Setter
	private Throwable cause;

	@Override
	@GetMapping(value = "/goods/getGoodsVoByGoodsId")
	public Result<GoodsVo> getGoodsVoByGoodsId(@PathVariable("goodsId") long goodsId) {
		log.error("feign 插入日志失败", cause);
		return null;
	}

	@Override
	public Result<Boolean> reduceStock(GoodsVo goods) {
		log.error("feign 插入日志失败", cause);
		return null;
	}

	@Override
	public Result<List<GoodsVo>> listGoodsVo() {
		log.error("feign 插入日志失败", cause);
		return null;
	}
}
