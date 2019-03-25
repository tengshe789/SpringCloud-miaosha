package tech.tengshe789.miaosha.common.core.constants;

import lombok.*;
import lombok.experimental.UtilityClass;

/**
 * @program: -miaosha
 * @description: 业务代码
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-25 09:09
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
public class CodeMsgConstants {

	private int code;
	private String msg;

	//正确码
	public static CodeMsgConstants SUCCESS = new CodeMsgConstants(0, "服务success");
	//错误码
	public static CodeMsgConstants FAIL = new CodeMsgConstants(1, "服务异常");

	//通用的错误码 1001XX
	public static CodeMsgConstants SERVER_ERROR = new CodeMsgConstants(100100, "服务端异常");
	public static CodeMsgConstants BIND_ERROR = new CodeMsgConstants(100101, "参数校验异常：%s");
	public static  CodeMsgConstants REQUEST_ILLEGAL = new CodeMsgConstants(100102, "非法请求");

	//用户权限模块的错误码 2001XX
	public static CodeMsgConstants MENU_WITH_SUBORDINATES_CANNOT_BE_DELETED = new CodeMsgConstants(200100, "菜单含有下级不能删除");
	public static CodeMsgConstants ORIGINAL_PASSWORD_WRONG = new CodeMsgConstants(200101, "原密码错误，修改失败");
	public static CodeMsgConstants FAILED_TO_OBTAIN_CURRENT_USER_INFORMATION = new CodeMsgConstants(200102, "获取当前用户信息失败~");


	//登录与认证模块 5001XX
	public static CodeMsgConstants SESSION_ERROR = new CodeMsgConstants(500110, "Session不存在或者已经失效~");
	public static CodeMsgConstants PASSWORD_EMPTY = new CodeMsgConstants(500111, "登录密码不能为空~");
	public static CodeMsgConstants MOBILE_EMPTY = new CodeMsgConstants(500112, "手机号不能为空~");
	public static CodeMsgConstants MOBILE_ERROR = new CodeMsgConstants(500113, "手机号格式错误~");
	public static CodeMsgConstants MOBILE_NOT_EXIST = new CodeMsgConstants(500114, "手机号不存在~");
	public static CodeMsgConstants PASSWORD_ERROR = new CodeMsgConstants(500115, "密码错误~");
	public static CodeMsgConstants USER_INFORMATION_IS_EMPTY = new CodeMsgConstants(500116, "用户信息为空");
	public static CodeMsgConstants EXIT_FAILED_TOKEN_EMPTY = new CodeMsgConstants(500117, "退出失败，token 为空");
	public static CodeMsgConstants EXIT_FAILED_TOKEN_INVALID = new CodeMsgConstants(500117, "退出失败，token 无效");

	//商品模块 5002XX

	//订单模块 5003XX
	public static CodeMsgConstants ORDER_NOT_EXIST = new CodeMsgConstants(500301, "订单为空~");
	public static CodeMsgConstants ORDER_GOODSID_NULL = new CodeMsgConstants(500302, "获取订单信息错误~");

	//秒杀模块 5004XX
	public static CodeMsgConstants MIAO_SHA_OVER = new CodeMsgConstants(500400, "商品秒杀完了喔~");
	public static CodeMsgConstants CHONG_FU_MIAOSHA = new CodeMsgConstants(500401, "不能重复秒杀商品啊大兄弟！！！");
	public static final CodeMsgConstants MIAO_SHA_FAIL = new CodeMsgConstants(500402, "验证码引擎故障");

	//可以返回带参数的校验码
	public CodeMsgConstants fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsgConstants(code, message);
	}

}

