package tech.tengshe789.miaosha.mall.goods.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.tengshe789.miaosha.common.core.constants.ServiceNameConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.mall.goods.api.feign.factories.RemoteGoodsServiceFallbackFactory;
import tech.tengshe789.miaosha.mall.goods.api.vo.GoodsVo;

import java.util.List;

/**
 * @program: -miaosha
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
@FeignClient(value = ServiceNameConstants.GOODS_SERVICE, fallbackFactory = RemoteGoodsServiceFallbackFactory.class)
public interface RemoteGoodsService {


	@GetMapping(value = "/goods/getGoodsVoByGoodsId")
	public Result<GoodsVo> getGoodsVoByGoodsId(@PathVariable("goodsId") long goodsId);

	@GetMapping("/goods/reduceStock")
	public Result<Boolean> reduceStock(GoodsVo goods);

	@GetMapping("/listGoodsVo")
	public Result<List<GoodsVo>> listGoodsVo();
}
