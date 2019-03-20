package tech.tengshe789.miaosha.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tech.tengshe789.miaosha.upms.entity.SysRole;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lengleng
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	List<SysRole> listRolesByUserId(Long userId);
}
