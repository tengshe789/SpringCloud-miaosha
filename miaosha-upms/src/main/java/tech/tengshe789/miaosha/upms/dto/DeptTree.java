package tech.tengshe789.miaosha.upms.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lengleng
 * @date 2018/1/20
 * 部门树
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode {
	private String name;
}
