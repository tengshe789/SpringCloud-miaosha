package tech.tengshe789.miaosha.common.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 日志表
 * </p>
 */
@Data
@ApiModel(value = "日志表")
@Accessors(chain = true)
public class SysLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value="编号",name="id",example="")
	private Long id;
	/**
	 * 日志类型
	 */
	@NotBlank(message = "日志类型不能为空")
	@ApiModelProperty(value = "日志类型")
	private String type;

	/**
	 * 日志标题
	 */
	@NotBlank(message = "日志标题不能为空")
	@ApiModelProperty(value = "日志标题")
	private String title;

	/**
	 * 创建者
	 */
	@ApiModelProperty(value = "创建者")
	private String createBy;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;

	/**
	 * 操作IP地址
	 */
	@ApiModelProperty(value = "操作IP地址")
	private String remoteAddr;

	/**
	 * 用户代理
	 */
	@ApiModelProperty(value = "用户代理")
	private String userAgent;

	/**
	 * 请求URI
	 */
	@ApiModelProperty(value = "请求URI")
	private String requestUri;

	/**
	 * 操作方式
	 */
	@ApiModelProperty(value = "操作方式")
	private String method;

	/**
	 * 操作提交的数据
	 */
	@ApiModelProperty(value = "操作提交的数据")
	private String params;

	/**
	 * 执行时间
	 */
	@ApiModelProperty(value = "执行时间")
	private Long time;

	/**
	 * 异常信息
	 */
	@ApiModelProperty(value = "异常信息")
	private String exception;

	/**
	 * 服务ID
	 */
	@ApiModelProperty(value = "服务ID")
	private String serviceId;

	/**
	 * 删除标记
	 */
	@ApiModelProperty(value = "删除标记")
	@TableLogic
	private String delFlag;

}
