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
import java.time.LocalDateTime;

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author lengleng
 */
@Data
//这个注解会生成equals(Object other) 和 hashCode()方法。 它默认仅使用该类中定义的属性且不调用父类的方法
//可通过callSuper=true解决上一点问题。让其生成的方法中调用父类的方法。
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dept")
@ApiModel(value = "部门表")
public class SysDept extends Model<SysDept> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "dept_id", type = IdType.AUTO)
	private Integer deptId;
	/**
	 * 部门名称
	 */
	@NotBlank(message = "部门名称不能为空")
	@ApiModelProperty(value="返回码",name="code",example="100100")
	private String name;
	/**
	 * 排序
	 */
	@ApiModelProperty(value="排序",name="sort",example="")
	private Integer sort;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",name="createTime",example="")
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value="修改时间",name="updateTime",example="")
	private LocalDateTime updateTime;

	@ApiModelProperty(value="父部门ID",name="parentId",example="")
	private Integer parentId;

	/**
	 * 是否删除  -1：已删除  0：正常
	 * @TableLogic：表字段逻辑处理注解（逻辑删除）
	 */
	@TableLogic
	private String delFlag;

}
