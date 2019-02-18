package tech.tengshe789.miaosha.upms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 部门关系表
 * </p>
 *
 * @author lengleng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dept_relation")
@ApiModel(value = "部门关系表")
public class SysDeptRelation extends Model<SysDeptRelation> {

	private static final long serialVersionUID = 1L;

	/**
	 * 祖先节点
	 */
	@ApiModelProperty(value="祖先节点",name="ancestor",example="")
	private Integer ancestor;
	/**
	 * 后代节点
	 */
	@ApiModelProperty(value="后代节点",name="descendant",example="")
	private Integer descendant;


}
