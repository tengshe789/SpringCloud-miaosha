package cn.tengshe789.vo;

import cn.tengshe789.domain.OrderInfo;
import lombok.Data;

@Data
public class OrderDetailVo {
	private GoodsVo goods;
	private OrderInfo order;
}
