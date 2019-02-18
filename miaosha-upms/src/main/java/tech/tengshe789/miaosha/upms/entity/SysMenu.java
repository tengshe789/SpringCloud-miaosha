package tech.tengshe789.miaosha.upms.entity;

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
import java.time.LocalDateTime;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author lengleng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
@ApiModel(value = "菜单权限表")
public class SysMenu extends Model<SysMenu> {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@NotNull(message = "菜单ID不能为空")
	@TableId(value = "menu_id", type = IdType.INPUT)
	@ApiModelProperty(value="菜单ID",name="menuId",example="")
	private Integer menuId;
	/**
	 * 菜单名称
	 */
	@NotBlank(message = "菜单名称不能为空")
	@ApiModelProperty(value="菜单名称",name="name",example="")
	private String name;
	/**
	 * 菜单权限标识
	 */
	@ApiModelProperty(value="菜单权限标识",name="permission",example="")
	private String permission;
	/**
	 * 父菜单ID
	 */
	@ApiModelProperty(value="父菜单ID",name="parentId",example="")
	@NotNull(message = "菜单父ID不能为空")
	private Integer parentId;
	/**
	 * 图标
	 */
	@ApiModelProperty(value="图标",name="icon",example="")
	private String icon;
	/**
	 * VUE页面
	 */
	@ApiModelProperty(value="",name="",example="")
	private String component;
	/**
	 * 排序值
	 */
	@ApiModelProperty(value="",name="",example="")
	private Integer sort;
	/**
	 * 菜单类型 （0菜单 1按钮）
	 */
	@ApiModelProperty(value="",name="",example="")
	@NotNull(message = "菜单类型不能为空")
	private String type;
	/**
	 * 路由缓冲
	 */
	@ApiModelProperty(value="",name="",example="100100")
	private String keepAlive;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="",name="code",example="100100")
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="返回码",name="code",example="100100")
	private LocalDateTime updateTime;
	/**
	 * 0--正常 1--删除
	 */
	@ApiModelProperty(value="返回码",name="code",example="100100")
	@TableLogic
	private String delFlag;
	/**
	 * 前端URL
	 */
	@ApiModelProperty(value="返回码",name="code",example="100100")
	private String path;


}
