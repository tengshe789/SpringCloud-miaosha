package tech.tengshe789.miaosha.sys.api.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.tengshe789.miaosha.sys.api.entity.SysUser;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends SysUser {
	/**
	 * 角色ID
	 */
	private List<Integer> role;

	/**
	 * 新密码
	 */
	private String newpassword1;
}
