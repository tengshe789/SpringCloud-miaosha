package tech.tengshe789.miaosha.sys.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import tech.tengshe789.miaosha.sys.api.entity.SysUser;

import java.io.Serializable;

/**
 * @author lengleng
 * <p>
 * commit('SET_ROLES', data)
 * commit('SET_NAME', data)
 * commit('SET_AVATAR', data)
 * commit('SET_INTRODUCTION', data)
 * commit('SET_PERMISSIONS', data)
 */
@Data
@Accessors(chain = true)
public class UserInfo implements Serializable {
	/**
	 * 用户基本信息
	 */
	private SysUser sysUser;
	/**
	 * 权限标识集合
	 */
	private String[] permissions;

	/**
	 * 角色集合
	 */
	private Integer[] roles;
}
