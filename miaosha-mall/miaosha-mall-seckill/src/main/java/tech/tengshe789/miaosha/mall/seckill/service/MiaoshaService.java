package tech.tengshe789.miaosha.mall.seckill.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.tengshe789.miaosha.common.security.entity.MiaoshaUser;
import tech.tengshe789.miaosha.mall.goods.service.GoodsService;
import tech.tengshe789.miaosha.mall.goods.vo.GoodsVo;
import tech.tengshe789.miaosha.mall.order.entity.MiaoshaOrder;
import tech.tengshe789.miaosha.mall.order.entity.OrderInfo;
import tech.tengshe789.miaosha.mall.order.service.OrderService;
import tech.tengshe789.miaosha.upms.entity.SysUser;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class MiaoshaService {
    private static char[] ops = new char[] {'+', '-', '*'};
	private final GoodsService goodsService;
	private final OrderService orderService;

     @Transactional
    public OrderInfo miaosha(SysUser user, GoodsVo goods) {
        //秒杀到了，减库存，下订单。写入秒杀订单
         boolean success = goodsService.reduceStock(goods);
         if (success){
             //order_info.miashao_order,生成订单
             return orderService.createOrder(user,goods);
         }else {
             setGoodsOver(goods.getId());
            return null;
         }
    }


    public long getMiaoshaResult(Long userId, long goodsId) {
        MiaoshaOrder order = orderService.getMiaoshaUserByUserIdGoodsId(userId, goodsId);
        if (order != null){
             return order.getId();
        }else {
            boolean isOver = getGoodsOver(goodsId);
            if (isOver){
                return  -1;
            }else {
                return 0;
            }
        }
    }

    private boolean getGoodsOver(long goodsId) {
     	//TODO
//        return redisService.exists(MiaoshaKey.isOver,""+goodsId );
		return false;
    }

    private void setGoodsOver(Long goodsId) {
     	//TODO
//        redisService.set(MiaoshaKey.isOver, ""+goodsId, true);
    }

    public void reset(List<GoodsVo> goodsList) {
        goodsService.resetStock(goodsList);
        orderService.deleteAllOrders();
    }

    public String creatMiaoshaPath(MiaoshaUser user, long goodsId) {
        if (user == null || goodsId <= 0){
            return null;
        }
        //TODO
//        String str = MD5Util.md5(UUIDUtil.uuid() + "5211314");
//        redisService.set(MiaoshaKey.getMiaoshaPath,""+user.getId()+"_"+goodsId,str);
//        return str;
		return null;
    }

    public boolean checkPath(MiaoshaUser user, long goodsId, String path) {
     	//TODO
//         if (user == null || path == null){
//             return false;
//         }
//        String oldPath = redisService.get(MiaoshaKey.getMiaoshaPath, "" + user.getId() + "_" + goodsId, String.class);
//         return path.equalsIgnoreCase(oldPath);
		return true;
    }

    public BufferedImage creatVerifyCode(MiaoshaUser user, long goodsId) {
        if(user == null || goodsId <=0) {
            return null;
        }
        int width = 80;//宽度80
        int height = 32;//高度32
        //创建BufferedImage内存图形
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 画笔
        Graphics g = image.getGraphics();
        // 背景颜色
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);//背景颜色填充
        // 用黑画笔画框框
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // 生成随机坐标
        Random rdm = new Random();
        // 生成50个干扰的点
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // 生成验证码
        String verifyCode = generateVerifyCode(rdm);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(verifyCode, 8, 24);
        g.dispose();//销毁画笔
        //把验证码存到redis中
        int rnd = calc(verifyCode);
        //TODO
//        redisService.set(MiaoshaKey.getMiaoshaVerifyCode, user.getId()+","+goodsId, rnd);
        //输出图片	
        return image;
    }

    public boolean checkVerifyCode(MiaoshaUser user, long goodsId, int verifyCode) {
     	//TODO
//        if(user == null || goodsId <=0) {
//            return false;
//        }
//        Integer codeOld = redisService.get(MiaoshaKey.getMiaoshaVerifyCode, user.getId()+","+goodsId, Integer.class);
//        if(codeOld == null || codeOld - verifyCode != 0 ) {
//            return false;
//        }
//        redisService.delete(MiaoshaKey.getMiaoshaVerifyCode, user.getId()+","+goodsId);
        return true;
    }

    /**
     * 计算验证码的结果
     *
     * JSR 223中规范了在Java虚拟机上运行的脚本语言与Java程序之间的交互方式。
     * JSR 233是JavaSE6的一部分，在Java表中API中的包是javax.script。
     * 目前Java虚拟机支持比较多的脚本语言，比较流行的有JavaScript、Scala、JRuby、Jython和Groovy等。 
     * @param exp
     * @return
     */
    private static int calc(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");//v8引擎
            return (Integer)engine.eval(exp);
        }catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 生成数学验证码 + - *
     * */
    private String generateVerifyCode(Random rdm) {
        //随机生成10以内的3个数字
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        //随机取一个操作符
        char op1 = ops[rdm.nextInt(3)];
        char op2 = ops[rdm.nextInt(3)];
        String exp = ""+ num1 + op1 + num2 + op2 + num3;
        return exp;
    }
}
