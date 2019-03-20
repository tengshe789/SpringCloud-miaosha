package tech.tengshe789.miaosha.upms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author lengleng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_role")
@ApiModel(value = "用户角色表")
@Accessors(chain = true)
public class SysUserRole extends Model<SysUserRole> {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(value="",name="",example="")
	private Long userId;
	/**
	 * 角色ID
	 */
	@ApiModelProperty(value="",name="",example="")
	private Integer roleId;

}
