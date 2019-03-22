package tech.tengshe789.miaosha.sys.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.tengshe789.miaosha.sys.api.entity.SysUserRole;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author lengleng
 */
public interface SysUserRoleService extends IService<SysUserRole> {

	/**
	 * 根据用户Id删除该用户的角色关系
	 *
	 * @param userId 用户ID
	 * @return boolean
	 * @author 寻欢·李
	 */
	Boolean deleteByUserId(Long userId);
}
