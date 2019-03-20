package tech.tengshe789.miaosha.mall.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("")
@ApiModel(value = "")
public class MiaoshaOrder extends Model<MiaoshaOrder> {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
}
