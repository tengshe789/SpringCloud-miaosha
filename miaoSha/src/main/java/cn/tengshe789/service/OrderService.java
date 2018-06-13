package cn.tengshe789.service;

import cn.tengshe789.dao.OrderDao;
import cn.tengshe789.domain.MiaoshaOrder;
import cn.tengshe789.domain.MiaoshaUser;
import cn.tengshe789.domain.OrderInfo;
import cn.tengshe789.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Component
public class OrderService {
    @Autowired
    OrderDao orderDao;

    public MiaoshaOrder getMiaoshaUserByUserIdGoodsId(long id, long goodsId) {
        return  orderDao.getMiaoshaUserByUserIdGoodsId(id,goodsId);
    }

    @Transactional
    public OrderInfo creatOrder(MiaoshaUser user, GoodsVo goods) {
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);

        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId=orderDao.insert(orderInfo);
        MiaoshaOrder miaoshaOrder=new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setUserId(user.getId());

        orderDao.insertMiaoshaOrder(miaoshaOrder);
        return orderInfo;
    }
}
