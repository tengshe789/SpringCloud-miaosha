package tech.tengshe789.miaosha.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 客户端信息
 * </p>
 *
 * @author lengleng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_oauth_client_details")
@ApiModel(value = "客户端信息")
public class SysOauthClientDetails extends Model<SysOauthClientDetails> {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户端ID
	 */
	@ApiModelProperty(value="返回码",name="code",example="100100")
	@NotBlank(message = "client_id 不能为空")
	@TableId(value = "client_id", type = IdType.INPUT)
	private String clientId;

	/**
	 * 客户端密钥
	 */
	@ApiModelProperty(value="返回码",name="code",example="100100")
	@NotBlank(message = "client_secret 不能为空")
	private String clientSecret;

	/**
	 * 资源ID
	 */
	@ApiModelProperty(value="返回码",name="code",example="100100")
	private String resourceIds;

	/**
	 * 作用域
	 */
	@ApiModelProperty(value="",name="",example="")
	@NotBlank(message = "scope 不能为空")
	private String scope;

	/**
	 * 授权方式（A,B,C）
	 */
	@ApiModelProperty(value="",name="",example="")
	private String authorizedGrantTypes;

	@ApiModelProperty(value="",name="",example="")
	private String webServerRedirectUri;

	@ApiModelProperty(value="",name="",example="")
	private String authorities;

	/**
	 * 请求令牌有效时间
	 */
	@ApiModelProperty(value="",name="",example="")
	private Integer accessTokenValidity;

	/**
	 * 刷新令牌有效时间
	 */
	@ApiModelProperty(value="",name="",example="")
	private Integer refreshTokenValidity;

	/**
	 * 扩展信息
	 */
	@ApiModelProperty(value="",name="",example="")
	private String additionalInformation;

	/**
	 * 是否自动放行
	 */
	@ApiModelProperty(value="",name="",example="")
	private String autoapprove;

	@Override
	protected Serializable pkVal() {
		return this.clientId;
	}

}
