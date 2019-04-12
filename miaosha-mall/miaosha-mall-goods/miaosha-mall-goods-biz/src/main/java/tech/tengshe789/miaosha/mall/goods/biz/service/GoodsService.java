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
	//乐观锁冲突最大重试次数
	private static final int DEFAULT_MAX_RETRIES = 5;

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
	 *
	 * https://www.cnblogs.com/laoyeye/p/8097684.html
	 *
     * @param goods
	 * @return true
     */
	public Boolean reduceStock(GoodsVo goods) {
		int numAttempts = 0;
		int reduceStock = 0;
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
		g.setVersion(goods.getVersion());
		do {
			numAttempts++;
			try {
				g.setVersion(goodsDao.getVersionByGoodsId(goods.getId()));
				reduceStock = goodsDao.reduceStock(g);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (reduceStock != 0)
				break;
		} while (numAttempts < DEFAULT_MAX_RETRIES);

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
