package tech.tengshe789.miaosha.sys.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 系统社交登录账号表
 *
 * @author code generator
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_social_details")
@ApiModel(value = "系统社交登录账号表")
public class SysSocialDetails extends Model<SysSocialDetails> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主鍵
	 */
	@TableId
	@ApiModelProperty(value="",name="",example="")
	private Integer id;
	/**
	 * 类型
	 */
	@NotBlank(message = "类型不能为空")
	@ApiModelProperty(value="",name="",example="")
	private String type;
	/**
	 * 描述
	 */
	@ApiModelProperty(value="",name="",example="")
	private String remark;
	/**
	 * appid
	 */
	@ApiModelProperty(value="",name="",example="")
	@NotBlank(message = "账号不能为空")
	private String appId;
	/**
	 * app_secret
	 */
	@ApiModelProperty(value="",name="",example="")
	@NotBlank(message = "密钥不能为空")
	private String appSecret;
	/**
	 * 回调地址
	 */
	@ApiModelProperty(value="",name="",example="")
	private String redirectUrl;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="",name="",example="")
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="",name="",example="")
	private LocalDateTime updateTime;
	/**
	 * 删除标记
	 */
	@ApiModelProperty(value="",name="",example="")
	@TableLogic
	private String delFlag;

	/**
	 * 租户ID
	 */
	@ApiModelProperty(value="",name="",example="")
	private Integer tenantId;

}
