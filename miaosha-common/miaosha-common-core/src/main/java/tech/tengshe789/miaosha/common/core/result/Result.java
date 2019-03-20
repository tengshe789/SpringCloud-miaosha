package tech.tengshe789.miaosha.common.core.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import tech.tengshe789.miaosha.common.core.constants.CodeMsgConstants;

import java.io.Serializable;

/**
 * @program: -miaosha
 * @description: 统一API响应结果封装
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-25 08:46
 **/
@Data
@ApiModel(value = "结果返回类型")
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 通用返回码
	 */
	@ApiModelProperty(value="返回码",name="code",example="100100")
	private int code;

	/**
	 * 通用返回消息
	 */
	@ApiModelProperty(value="返回消息",name="msg",example="服务端异常")
	private String msg;

	/**
	 * 结果对象
	 */
	@ApiModelProperty(value="数据",name="data",example="SERVER_ERROR")
	private T data;

	/**
	 *  成功时候的调用
	 * */
	public static  <T> Result<T> success(T data){
		return new Result<T>(data);
	}

	/**
	 *  失败时候的调用
	 * */
	public static  <T> Result<T> error(CodeMsgConstants codeMsg){
		return new Result<T>(codeMsg);
	}

	public static  <T> Result<T> error(CodeMsgConstants codeMsg , T data){
		return new Result<T>(codeMsg,data);
	}

	public Result (int code) {
		this.code=code;
	}

	@Deprecated
	private Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Result (T data) {
		this.data=data;
	}

	private Result(CodeMsgConstants codeMsg) {
		if(codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
		}
	}

	private Result(CodeMsgConstants codeMsg ,T data) {
		if(codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
		}
		this.data = data;
	}

}
