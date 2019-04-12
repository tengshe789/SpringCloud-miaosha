package tech.tengshe789.miaosha.sys.biz.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.tengshe789.miaosha.common.core.constants.CodeMsgConstants;
import tech.tengshe789.miaosha.common.core.constants.StatusConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.sys.api.dto.UserDTO;
import tech.tengshe789.miaosha.sys.api.dto.UserInfo;
import tech.tengshe789.miaosha.sys.api.entity.*;
import tech.tengshe789.miaosha.sys.biz.mapper.SysUserMapper;
import tech.tengshe789.miaosha.sys.biz.service.*;
import tech.tengshe789.miaosha.sys.api.vo.UserVO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 */
@Slf4j
@Service("sysUserServiceImpl")
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
	private final SysUserRoleService sysUserRoleService;
	private final SysRoleService sysRoleService;

	/**
	 * 保存用户信息
	 *
	 * @param userDto DTO 对象
	 * @return success/fail
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveUser(UserDTO userDto) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setDelFlag(StatusConstants.STATUS_NORMAL);
		sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
		baseMapper.insert(sysUser);
		List<SysUserRole> userRoleList = userDto.getRole()
				.stream().map(roleId -> {
					return new SysUserRole().setUserId(sysUser.getUserId()).setRoleId(roleId);
				}).collect(Collectors.toList());
		return sysUserRoleService.saveBatch(userRoleList);
	}

	/**
	 * 通过查用户的全部信息
	 *
	 * @param sysUser 用户
	 * @return
	 */
	@Override
	public UserInfo findUserInfo(SysUser sysUser) {
		UserInfo userInfo = new UserInfo();
		userInfo.setSysUser(sysUser);
		//设置角色列表  （ID）
		List<Integer> roleIds = sysRoleService.findRolesByUserId(sysUser.getUserId())
			.stream()
			.map(SysRole::getRoleId)
			.collect(Collectors.toList());
		userInfo.setRoles(ArrayUtil.toArray(roleIds, Integer.class));
		return userInfo;
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id 用户ID
	 * @return 用户信息
	 */
	@Override
	public UserVO selectUserVoById(Long id) {
		//TODO 从缓存中取出uservo
		return baseMapper.getUserVoById(id);
	}

	/**
	 * 通过username查询用户信息
	 *
	 * @param username
	 * @return
	 */
	@Override
	public UserVO selectUserVoByUsername(String username) {
		return baseMapper.getUserVoByUsername(username);
	}

	/**
	 * 删除用户
	 *
	 * @param sysUser 用户
	 * @return Boolean
	 */
	@Override
	@CacheEvict(value = "user_details", key = "#sysUser.username")
	public Boolean deleteUserById(SysUser sysUser) {
		sysUserRoleService.deleteByUserId(sysUser.getUserId());
		this.removeById(sysUser.getUserId());
		return Boolean.TRUE;
	}

	/**
	 * 分页查询用户信息（含有角色信息）
	 *
	 * @param page    分页对象
	 * @param userDTO 参数列表
	 * @return
	 */
	@Override
	public IPage getUsersWithRolePage(Page page, UserDTO userDTO) {
		return baseMapper.getUserVosPage(page, userDTO);
	}

	@Override
	@CacheEvict(value = "user_details", key = "#userDto.username")
	public Result<Boolean> updateUserInfo(UserDTO userDto) {
		UserVO userVO = baseMapper.getUserVoByUsername(userDto.getUsername());
		SysUser sysUser = new SysUser();
		if (StrUtil.isNotBlank(userDto.getPassword())
				&& StrUtil.isNotBlank(userDto.getNewpassword1())) {
			if (ENCODER.matches(userDto.getPassword(), userVO.getPassword())) {
				sysUser.setPassword(ENCODER.encode(userDto.getNewpassword1()));
			} else {
				log.warn("原密码错误，修改密码失败:{}", userDto.getUsername());
				return Result.error(CodeMsgConstants.ORIGINAL_PASSWORD_WRONG);
			}
		}
		sysUser.setPhone(userDto.getPhone());
		sysUser.setUserId(userVO.getUserId());
		sysUser.setAvatar(userDto.getAvatar());
		return Result.success(this.updateById(sysUser));
	}

	@Override
	@CacheEvict(value = "user_details", key = "#userDto.username")
	public Boolean updateUser(UserDTO userDto) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setUpdateTime(LocalDateTime.now());

		if (StrUtil.isNotBlank(userDto.getPassword())) {
			sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
		}
		this.updateById(sysUser);

		sysUserRoleService.remove(Wrappers.<SysUserRole>update().lambda()
				.eq(SysUserRole::getUserId, userDto.getUserId()));
		userDto.getRole().forEach(roleId -> {
			SysUserRole userRole = new SysUserRole().setUserId(sysUser.getUserId()).setRoleId(roleId);
			userRole.insert();
		});
		return Boolean.TRUE;
	}

	public static void main(String[] args) {
		System.out.println(ENCODER.matches("123456", "$10$QOfWxxFyAMmEEmnuw9UI/..1s4B4eF/u9PzE2ZaGO.ij9YfmcUy.u"));
		System.out.println(ENCODER.encode(""));
	}

}
