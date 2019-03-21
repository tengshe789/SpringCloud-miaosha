package tech.tengshe789.miaosha.common.core.constants;

/**
 * @program: -miaosha
 * @description: 通用约束
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-11 09:57
 **/
public interface CommonConstants {
	/**
	 * 路由存放
	 */
	String ROUTE_KEY = "gateway_route_key";

	/**
	 * spring boot admin 事件key
	 */
	String EVENT_KEY = "event_key";

	/**
	 * 验证码前缀
	 */
	String DEFAULT_CODE_KEY = "DEFAULT_CODE_";

	String GOODS_LIST_KEY = "GOODS_LIST_";

	String GOODS_DETAIL_KEY = "GOODS_DETAIL_";

	String GOODS_STOCK_KEY =  "GOODS_STOCK_";

	String ISOVER = "GO_";

	String MIAOSHA_PATH_KEY = "MIAOSHA_PATH_";

	/**
	 *  验证码时间设置长一点，本次设置的5分钟
	 */
	String MIAOSHA_CODE_KEY  = "MIAOSHA_CODE_";

//	String token = new MiaoshaUserKey(3600*24*2,"tk");

	String MIAOSHA_ORDER_KEY = "MIAOSHA_ORDER_";

	/**
	 * 删除
	 */
	String STATUS_DEL = "1";
	/**
	 * 正常
	 */
	String STATUS_NORMAL = "0";

	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";
}
