package tech.tengshe789.miaosha.sys.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author lengleng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_menu")
@ApiModel(value = "角色菜单表")
public class SysRoleMenu extends Model<SysRoleMenu> {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	@ApiModelProperty(value="",name="",example="")
	private Integer roleId;
	/**
	 * 菜单ID
	 */
	@ApiModelProperty(value="",name="",example="")
	private Integer menuId;
}
