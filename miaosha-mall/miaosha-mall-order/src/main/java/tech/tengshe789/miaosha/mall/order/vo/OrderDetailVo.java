package tech.tengshe789.miaosha.mall.order.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import tech.tengshe789.miaosha.mall.goods.vo.GoodsVo;
import tech.tengshe789.miaosha.mall.order.entity.OrderInfo;

@Data
@Accessors(chain = true)
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo order;
}
