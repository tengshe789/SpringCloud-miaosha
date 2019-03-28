package tech.tengshe789.miaosha.mall.order.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import tech.tengshe789.miaosha.mall.goods.api.vo.GoodsVo;
import tech.tengshe789.miaosha.mall.order.api.entity.OrderInfo;

@Data
@Accessors(chain = true)
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo order;
}
