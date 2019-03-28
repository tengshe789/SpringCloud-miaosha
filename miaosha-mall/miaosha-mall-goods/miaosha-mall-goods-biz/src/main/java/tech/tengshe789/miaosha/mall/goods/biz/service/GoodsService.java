package tech.tengshe789.miaosha.mall.goods.biz.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaosha.mall.goods.api.entity.MiaoshaGoods;
import tech.tengshe789.miaosha.mall.goods.api.vo.GoodsVo;
import tech.tengshe789.miaosha.mall.goods.biz.dao.GoodsDao;

import java.util.List;

@Service("goodsService")
@AllArgsConstructor
public class GoodsService {
	private final GoodsDao goodsDao;

    /*
     * 展示商品列表
     */
    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
		return goodsDao.getGoodsVoByGoodsId(goodsId);
	}

    /**
     * 减库存失败了就不返回订单
     * @param goods
     * @return
     */
	public Boolean reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        int reduceStock = goodsDao.reduceStock(g);
        return reduceStock > 0;
    }

    public void resetStock(List<GoodsVo> goodsList) {
    	goodsList.stream().forEach(goods -> {
			MiaoshaGoods g = new MiaoshaGoods();
			g.setGoodsId(goods.getId());
			g.setStockCount(goods.getStockCount());
			goodsDao.resetStock(g);
		});
    }
}
