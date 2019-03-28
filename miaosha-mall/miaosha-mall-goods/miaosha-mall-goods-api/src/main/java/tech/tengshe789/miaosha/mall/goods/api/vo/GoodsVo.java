package tech.tengshe789.miaosha.mall.goods.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import tech.tengshe789.miaosha.mall.goods.api.entity.Goods;

import java.util.Date;

@Data
@Accessors(chain = true)
public class GoodsVo extends Goods {
    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
