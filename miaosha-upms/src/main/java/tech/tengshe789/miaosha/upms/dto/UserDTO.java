package tech.tengshe789.miaosha.upms.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.tengshe789.miaosha.upms.entity.SysUser;

import java.util.List;

/**
 * @author lengleng
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends SysUser {
	/**
	 * 角色ID
	 */
	private List<Integer> role;

	private Integer deptId;

	/**
	 * 新密码
	 */
	private String newpassword1;
}
