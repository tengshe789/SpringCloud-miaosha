package tech.tengshe789.miaosha.sys.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author lengleng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
@ApiModel(value = "角色表")
public class SysRole extends Model<SysRole> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="",name="",example="")
	@TableId(value = "role_id", type = IdType.AUTO)
	private Integer roleId;

	@NotBlank(message = "角色名称 不能为空")
	@ApiModelProperty(value="",name="",example="")
	private String roleName;

	@NotBlank(message = "角色标识 不能为空")
	@ApiModelProperty(value="",name="",example="")
	private String roleCode;

	@NotBlank(message = "角色描述 不能为空")
	@ApiModelProperty(value="",name="",example="")
	private String roleDesc;

	@NotNull(message = "数据权限类型 不能为空")
	@ApiModelProperty(value="",name="",example="")
	private Integer dsType;

	@ApiModelProperty(value="",name="",example="")
	private String dsScope;

	@ApiModelProperty(value="",name="",example="")
	private LocalDateTime createTime;
	@ApiModelProperty(value="",name="",example="")
	private LocalDateTime updateTime;
	/**
	 * 删除标识（0-正常,1-删除）
	 */
	@TableLogic
	@ApiModelProperty(value="",name="",example="")
	private String delFlag;

	@Override
	@ApiModelProperty(value="",name="",example="")
	protected Serializable pkVal() {
		return this.roleId;
	}

}
