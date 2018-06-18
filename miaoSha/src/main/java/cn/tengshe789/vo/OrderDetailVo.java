package cn.tengshe789.vo;

import cn.tengshe789.domain.OrderInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo order;
}
