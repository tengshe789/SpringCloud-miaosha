package tech.tengshe789.miaosha.mall.seckill.biz.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.tengshe789.miaosha.common.core.constants.CommonConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.mall.goods.api.feign.RemoteGoodsService;
import tech.tengshe789.miaosha.mall.goods.api.vo.GoodsVo;
import tech.tengshe789.miaosha.mall.order.api.entity.MiaoshaOrder;
import tech.tengshe789.miaosha.mall.order.api.entity.OrderInfo;
import tech.tengshe789.miaosha.mall.order.api.feign.RemoteOrderService;
import tech.tengshe789.miaosha.sys.api.entity.SysUser;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

@Service
@Component
@AllArgsConstructor
public class MiaoshaService {
	private final RemoteGoodsService goodsService;
	private final RemoteOrderService orderService;
	private final RedisTemplate redisTemplate;

	@Transactional(rollbackFor = Exception.class)
    public OrderInfo miaosha(SysUser user, GoodsVo goods) {
        //秒杀到了，减库存，下订单。写入秒杀订单
		Boolean success = goodsService.reduceStock(goods).getData();
         if (success){
             //order_info.miashao_order,生成订单
			 Result<OrderInfo> order = orderService.createOrder(user, goods);
			 OrderInfo data = order.getData();
			 return data;
		 } else {
             setGoodsOver(goods.getId());
            return null;
         }
    }

    public long getMiaoshaResult(Long userId, long goodsId) {
		MiaoshaOrder order = orderService.getMiaoshaUserByUserIdGoodsId(userId, goodsId).getData();
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
		return redisTemplate.hasKey(CommonConstants.GOODS_STOCK_KEY + goodsId);
    }

    private void setGoodsOver(Long goodsId) {
		redisTemplate.opsForValue().set(CommonConstants.GOODS_STOCK_KEY + goodsId, true);
    }

	@Deprecated
	public void reset(List<GoodsVo> goodsList) {
//        goodsService.resetStock(goodsList);
//        orderService.deleteOrders();
	}

	public String creatMiaoshaPath(SysUser user, long goodsId) {
        if (user == null || goodsId <= 0){
            return null;
        }
		String str = SecureUtil.md5(UUID.fastUUID() + "5211314");
		redisTemplate.opsForValue().set(CommonConstants.MIAOSHA_PATH_KEY + user.getUserId() + "_" + goodsId, str);
		return str;
    }

	/**
	 * 检查秒杀路径
	 *
	 * @param user
	 * @param goodsId
	 * @param path
	 * @return
	 */
	public boolean checkPath(SysUser user, long goodsId, String path) {
		if (user == null || path == null) {
			return false;
		}
		String oldPath = (String) redisTemplate.opsForValue().get(CommonConstants.MIAOSHA_PATH_KEY + user.getUserId() + "_" + goodsId);
		return path.equalsIgnoreCase(oldPath);
	}

	@Deprecated
	public BufferedImage creatVerifyCode(SysUser user, long goodsId) {
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
//		redisTemplate.opsForValue().set(MiaoshaKey.getMiaoshaVerifyCode, user.getId()+","+goodsId, rnd);
        //输出图片	
		return image;
	}

	@Deprecated
	public boolean checkVerifyCode(SysUser user, long goodsId, int verifyCode) {
		if (user == null || goodsId <= 0) {
			return false;
		}
//        Integer codeOld = redisTemplate.opsForValue().get(MiaoshaKey.getMiaoshaVerifyCode, user.getId()+","+goodsId, Integer.class);
//        if(codeOld == null || codeOld - verifyCode != 0 ) {
//            return false;
//        }
//		redisTemplate.delete(MiaoshaKey.getMiaoshaVerifyCode, user.getId()+","+goodsId);
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
    @Deprecated
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
    @Deprecated
    private String generateVerifyCode(Random rdm) {
        //随机生成10以内的3个数字
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);

		char[] ops = new char[] {'+', '-', '*'};

        //随机取一个操作符
        char op1 = ops[rdm.nextInt(3)];
        char op2 = ops[rdm.nextInt(3)];
        String exp = ""+ num1 + op1 + num2 + op2 + num3;
        return exp;
    }
}
