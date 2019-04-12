package tech.tengshe789.miaosha.mall.goods.biz.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaosha.mall.goods.api.entity.MiaoshaGoods;
import tech.tengshe789.miaosha.mall.goods.api.vo.GoodsVo;

import java.util.List;

@Mapper
@Component(("goodsDao"))
public interface GoodsDao {

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

	/**
	 * stock_count > 0 和 版本号实现乐观锁 防止超卖
	 *
	 * @param g
	 * @return
	 */
	@Update("update miaosha_goods set stock_count = stock_count - 1  version= version + 1 " +
		"where goods_id = #{goodsId} and stock_count > 0 and version = #{version}")
    public int reduceStock(MiaoshaGoods g);

    @Update("update miaosha_goods set stock_count = #{stockCount} where goods_id = #{goodsId}")
    public int resetStock(MiaoshaGoods g);

	// 获取最新版本号
	@Select("select version from miaosha_goods  where goods_id = #{goodsId}")
	public int getVersionByGoodsId(@Param("goodsId") long goodsId);

}
