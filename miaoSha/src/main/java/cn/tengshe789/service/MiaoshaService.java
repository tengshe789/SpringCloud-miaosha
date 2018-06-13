package cn.tengshe789.service;

import cn.tengshe789.dao.GoodsDao;
import cn.tengshe789.domain.Goods;
import cn.tengshe789.domain.MiaoshaUser;
import cn.tengshe789.domain.OrderInfo;
import cn.tengshe789.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
public class MiaoshaService {
    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

     @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //秒杀到了，减库存，下订单。写入秒杀订单
         goodsService.reduceStock(goods);
        //order_info.miashao_order
        return orderService.creatOrder(user,goods);

         //不能在自家不能调用别家的dao，但可以调用别家的service
//         Goods g=new Goods();
//         g.setId(goods.getId());
//         g.setGoodsStock(goods.getGoodsStock()-1);
    }
}
