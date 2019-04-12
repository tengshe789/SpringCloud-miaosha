package tech.tengshe789.miaosha.mall.seckill.biz.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.tengshe789.miaosha.common.core.constants.CodeMsgConstants;
import tech.tengshe789.miaosha.common.core.constants.CommonConstants;
import tech.tengshe789.miaosha.common.core.constants.RabbitInfo;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.common.mq.function.RabbitSender;
import tech.tengshe789.miaosha.common.security.annotation.Inner;
import tech.tengshe789.miaosha.common.security.entity.MiaoshaUser;
import tech.tengshe789.miaosha.common.security.exception.SystemException;
import tech.tengshe789.miaosha.mall.goods.api.feign.RemoteGoodsService;
import tech.tengshe789.miaosha.mall.goods.api.vo.GoodsVo;
import tech.tengshe789.miaosha.mall.order.api.entity.MiaoshaOrder;
import tech.tengshe789.miaosha.mall.order.api.feign.RemoteOrderService;
import tech.tengshe789.miaosha.mall.seckill.api.vo.MiaoshaMessage;
import tech.tengshe789.miaosha.mall.seckill.biz.service.LoadDataIntoCache;
import tech.tengshe789.miaosha.mall.seckill.biz.service.MiaoshaService;
import tech.tengshe789.miaosha.sys.api.entity.SysUser;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/miaosha")
@AllArgsConstructor
@CrossOrigin
public class MiaoshaController {
	private final RemoteGoodsService goodsService;
	private final RemoteOrderService orderService;
	private final RedisTemplate redisTemplate;
	private final MiaoshaService miaoshaService;
	private final RabbitSender rabbitSender;

	/**
	 * 重置系统
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public Result<Boolean> reset(Model model) {
		List<GoodsVo> goodsList = goodsService.listGoodsVo().getData();
		for (GoodsVo goods : goodsList) {
			goods.setStockCount(10);
			redisTemplate.opsForValue().set(CommonConstants.GOODS_STOCK_KEY + goods.getId(), 10);
			LoadDataIntoCache.localOverMap.put(goods.getId(), false);
		}
		redisTemplate.delete(CommonConstants.GET_MIAOSHA_ORDER_BY_UID_GID_KEY);
		redisTemplate.delete(CommonConstants.ISOVER);
		miaoshaService.reset(goodsList);
		return Result.success(true);
	}

	/**
	 * 生成秒杀的path
	 *
	 * @param model
	 * @param user
	 * @param goodsId
	 * @return
	 */
	@Inner
	@GetMapping("/path")
	public Result<String> getMiaoshaPath(Model model, SysUser user,
										 @RequestParam("goodsId") long goodsId) {
		model.addAttribute("user", user);
		if (user == null) {
			return Result.error(CodeMsgConstants.USER_INFORMATION_IS_EMPTY);
		}
		String path = miaoshaService.creatMiaoshaPath(user, goodsId);

		return Result.success(path);
	}

	/**
	 * 不推荐
	 *
	 * @param response
	 * @param model
	 * @param user
	 * @param goodsId
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
	public Result<String> getMiaoshaVerifyCode(HttpServletResponse response, Model model,
											   SysUser user,
											   @RequestParam("goodsId") long goodsId) {
		model.addAttribute("user", user);
		if (user == null) {
			return Result.error(CodeMsgConstants.MIAOSHA_USERNAME_NOT_FOUND);
		}
		BufferedImage img = miaoshaService.creatVerifyCode(user, goodsId);
		try {
			OutputStream outputStream = response.getOutputStream();
			ImageIO.write(img, "JPEG", outputStream);
			outputStream.flush();
			outputStream.close();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return Result.error(CodeMsgConstants.MIAO_SHA_FAIL);
		}
	}


	/**
	 * 进入秒杀
	 *
	 * @param model
	 * @param user
	 * @param goodsId
	 * @param path
	 * @return
	 */
	@Inner
	@RequestMapping(value = "/{path}/do_miaosha", method = RequestMethod.POST)
	public Result<Integer> miaosha(Model model, SysUser user,
								   @RequestParam("goodsId") long goodsId,
								   @PathVariable("path") String path) {
		model.addAttribute("user", user);
		if (user == null) {
			return Result.error(CodeMsgConstants.USER_INFORMATION_IS_EMPTY);
		}
		//验证path
		boolean check = miaoshaService.checkPath(user, goodsId, path);
		if (!check) {
			return Result.error(CodeMsgConstants.REQUEST_ILLEGAL);
		}
		//拿到初始化时的库存数量，判断库存是否没了，减少redis访问
		boolean over = LoadDataIntoCache.localOverMap.get(goodsId);
		if (over) {
			return Result.error(CodeMsgConstants.MIAO_SHA_OVER);
		}
		//预先在redis中减库存
		Long stock = redisTemplate.opsForValue().decrement(CommonConstants.GOODS_STOCK_KEY + goodsId);
		if (stock < 0) {
			return Result.error(CodeMsgConstants.MIAO_SHA_OVER);
		}
		//判断是否秒杀到了，防止一个人秒杀到多个产品
		MiaoshaOrder order = orderService.getMiaoshaUserByUserIdGoodsId(user.getUserId(), goodsId).getData();
		if (order != null) {
			return Result.error(CodeMsgConstants.CHONG_FU_MIAOSHA);
		}
		//构造对象
		MiaoshaMessage miaoshaMessage = new MiaoshaMessage().setGoodsId(goodsId).setUser(user);
		//入队
		rabbitSender.send(RabbitInfo.MIAOSHA_QUEUE, miaoshaMessage);
		return Result.success(0);//定义0代表排队中
	}

	/**
	 * 秒杀成功：返回orderId
	 * 库存不足：-1
	 * 排队中：0
	 *
	 * @param model
	 * @param user
	 * @param goodsId
	 * @return
	 */
	@Inner
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public Result<Long> miaoshaResult(Model model, SysUser user,
									  @RequestParam("goodsId") long goodsId) {
		model.addAttribute("user", user);
		if (user == null) {
		}
		long result = miaoshaService.getMiaoshaResult(user.getUserId(), goodsId);
		return Result.success(result);
	}

	//第二次的代码
//    @RequestMapping(value = "/do_miaosha",method = RequestMethod.POST)
//    @ResponseBody
//    public Result<OrderInfo> miaosha(Model model, MiaoshaUser user,
//                         @RequestParam("goodsId")long goodsId){
//        model.addAttribute("user",user);
//        if (user==null){
//            return Result.error(CodeMsg.SESSION_ERROR);
//        }
//        //判断库存
//        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
//        Integer stock = goods.getStockCount();
//        if (stock<0){
//            return Result.error(CodeMsg.MIAO_SHA_OVER);
//        }
//        //判断是否秒杀到了，防止一个人秒杀到多个产品
//        MiaoshaOrder order=orderService.getMiaoshaUserByUserIdGoodsId(user.getId(),goodsId);
//        if (order!=null){
//            return Result.error(CodeMsg.CHONG_FU_MIAOSHA);
//        }
//
//        //秒杀到了，减库存，下订单。写入秒杀订单
//        OrderInfo orderInfo=miaoshaService.miaosha(user,goods);
//        return Result.success(orderInfo);
//    }


//第一次的代码
//    @RequestMapping("/do_miaosha")
//    public String toList(Model model,MiaoshaUser user,
//                         @RequestParam("goodsId")long goodsId
//    ){
//        model.addAttribute("maioshauser",user);
//        if (user==null){
//            return "login";
//        }
//        //判断库存
//        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
//        Integer stock = goods.getStockCount();
//        if (stock<=0){
//            model.addAttribute("errmsg",CodeMsg.MIAO_SHA_OVER.getMsg());
//            return "miaosha_fail";
//        }
//        //判断是否秒杀到了，防止一个人秒杀到多个产品
//        MiaoshaOrder order=orderService.getMiaoshaUserByUserIdGoodsId(user.getId(),goodsId);
//        if (order!=null){
//            model.addAttribute("errmsg",CodeMsg.CHONG_FU_MIAOSHA.getMsg());
//            return "miaosha_fail";
//        }
//
//        //秒杀到了，减库存，下订单。写入秒杀订单
//        OrderInfo orderInfo=miaoshaService.miaosha(user,goods);
//        model.addAttribute("orderInfo",orderInfo);//将秒杀订单信息直接写入订单
//        model.addAttribute("goods",goods);//将商品信息直接写入订单
//        return "order_detail";
//    }

}
