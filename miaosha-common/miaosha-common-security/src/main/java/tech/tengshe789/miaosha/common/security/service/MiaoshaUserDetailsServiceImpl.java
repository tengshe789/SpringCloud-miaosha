package tech.tengshe789.miaosha.common.security.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaosha.common.core.constants.CommonConstants;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.common.security.entity.MiaoshaUser;
import tech.tengshe789.miaosha.common.security.exception.MiaoshaUsernameNotFoundException;
import tech.tengshe789.miaosha.sys.api.dto.UserInfo;
import tech.tengshe789.miaosha.sys.api.entity.SysUser;
import tech.tengshe789.miaosha.sys.api.feign.RemoteUserService;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: -miaosha
 * @description:
 * @author: tEngSHe789
 * @create: 2019-03-21 15:32
 **/
@Slf4j
@Service
@AllArgsConstructor
public class MiaoshaUserDetailsServiceImpl implements MiaoshaUserDetailsService{
	private final RemoteUserService remoteUserService;
	private final CacheManager cacheManager;

	/**
	 * 用户密码登录
	 *
	 * @param username 用户名
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		Cache cache = cacheManager.getCache("user_details");
		//从缓存中取
		if (cache != null && cache.get(username) != null) {
			return (MiaoshaUser) cache.get(username).get();
		}
		//调用远程服务从数据库查
		Result<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
		UserDetails userDetails = getUserDetails(result);
		cache.put(username, userDetails);
		return userDetails;
	}

	/**
	 * 根据社交登录code 登录
	 *
	 * @param inStr TYPE@CODE
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserBySocial(String inStr) {
		return getUserDetails(remoteUserService.social(inStr, SecurityConstants.FROM_IN));
	}

	/**
	 * 构建userdetails
	 *
	 * @param result 用户信息
	 * @return
	 */
	private UserDetails getUserDetails(Result<UserInfo> result) {
		if (result == null || result.getData() == null) {
			throw new MiaoshaUsernameNotFoundException();
		}

		UserInfo info = result.getData();
		Set<String> dbAuthsSet = new HashSet<>();
		if (ArrayUtil.isNotEmpty(info.getRoles())) {
			// 获取角色
			Arrays.stream(info.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
			// 获取资源
			dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));
		}

		Collection<? extends GrantedAuthority> authorities
			= AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
		SysUser user = info.getSysUser();
		boolean enabled = StrUtil.equals(user.getLockFlag(), CommonConstants.STATUS_NORMAL);
		// 构造security用户
		MiaoshaUser miaoshaUser = new MiaoshaUser(user.getUserId(), user.getUsername(), SecurityConstants.BCRYPT + user.getPassword(), enabled,
			true, true, !CommonConstants.STATUS_LOCK.equals(user.getLockFlag()), authorities);
		return miaoshaUser;
	}
}

