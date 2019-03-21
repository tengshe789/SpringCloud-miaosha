package tech.tengshe789.miaosha.mall.goods.controller;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import tech.tengshe789.miaosha.common.core.constants.CommonConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.mall.goods.service.GoodsService;
import tech.tengshe789.miaosha.mall.goods.vo.GoodsDetailVo;
import tech.tengshe789.miaosha.mall.goods.vo.GoodsVo;
import tech.tengshe789.miaosha.upms.entity.SysUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/goods")
@AllArgsConstructor
public class GoodsController {
	private final GoodsService goodsService;
	private final ThymeleafViewResolver thymeleafViewResolver;
	private final RedisTemplate redisTemplate;

    @RequestMapping(value="/to_list", produces="text/html")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, SysUser user
    , @RequestParam(required = false,defaultValue = "1",value = "page") Integer page) {
        model.addAttribute("user", user);
        //从redis找已经渲染出来的商品list
		String html = (String) redisTemplate.opsForValue().get(CommonConstants.GOODS_LIST_KEY + "*");
		if(!StrUtil.isEmpty(html)) {
			return html;
    	}
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        //手动渲染一下子
		IWebContext ctx =new WebContext(request,response,
			request.getServletContext(),request.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list",ctx);
		//为了优化访问速度，应对高并发，想把后端渲染出来的页面信息全部获取出来存到redis缓存中，这样每次访问就不用Thymeleaf进行渲染了。
		if(!StrUtil.isEmpty(html)) {
			redisTemplate.opsForValue().set(CommonConstants.GOODS_LIST_KEY,html);
        }
        return html;
    }

	/**
	 *  静态化的商品的详情页
	 * @param user
	 * @param goodsId
	 * @return
	 */
    @RequestMapping(value="/detail/{goodsId}")
    public Result<GoodsDetailVo> detail(SysUser user,
										@PathVariable("goodsId")long goodsId) {
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        long startAt = goods.getStartDate().getTime();//开始时间
        long endAt = goods.getEndDate().getTime();//结束时间
        long now = System.currentTimeMillis();//当前时间

        int miaoshaStatus = 0;// 秒杀状态
        int remainSeconds = 0; // 剩余秒数

        if(now < startAt ) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        GoodsDetailVo vo = new GoodsDetailVo()
								.setGoods(goods)
								.setUser(user)
								.setRemainSeconds(remainSeconds)
								.setMiaoshaStatus(miaoshaStatus);

        return Result.success(vo);
    }



//               /**
//               * @methodDesc: SSR的商品的详情页
//               */
//    @RequestMapping(value = "/to_detail2/{goodsId}", produces="text/html")
//    public String detail2(Model model, HttpServletRequest request, HttpServletResponse response,
//						  SysUser user,
//						  @PathVariable("goodsId")long goodsId) {
//        model.addAttribute("user", user);
////        取缓存
////        String html = redisService.get(GoodsKey.getGoodsDetail, ""+goodsId, String.class);
//        //如果缓存中非空
////        if(!StrUtil.isEmpty(html)){
////            return html;
////        }
////        *如果从redis中娶不到，就*手动渲染*
//        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
//        model.addAttribute("goods", goods);
//
//        long startAt = goods.getStartDate().getTime();
//        long endAt = goods.getEndDate().getTime();
//        long now = System.currentTimeMillis();
//
//        int miaoshaStatus = 0;
//        int remainSeconds = 0;
//        if(now < startAt ) {//秒杀还没开始，倒计时
//            miaoshaStatus = 0;
//            remainSeconds = (int)((startAt - now )/1000);
//        }else  if(now > endAt){//秒杀已经结束
//            miaoshaStatus = 2;
//            remainSeconds = -1;
//        }else {//秒杀进行中
//            miaoshaStatus = 1;
//            remainSeconds = 0;
//        }
//        model.addAttribute("miaoshaStatus", miaoshaStatus);
//        model.addAttribute("remainSeconds", remainSeconds);
//        SpringWebContext ctx = new SpringWebContext(request,response,
//                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
//        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);
//        if(!StringUtils.isEmpty(html)) {
//            redisService.set(GoodsKey.getGoodsDetail, ""+goodsId, html);
//        }
//        写到输出
//        return html;
//        }

}
