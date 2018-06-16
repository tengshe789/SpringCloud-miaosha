package cn.tengshe789.vo;

import cn.tengshe789.domain.OrderInfo;

public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo order;

    public GoodsVo getGoods() {
        return goods;
    }

    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }

    public OrderInfo getOrder() {
        return order;
    }

    public void setOrder(OrderInfo orderInfo) {
        this.order= orderInfo;
    }
}
