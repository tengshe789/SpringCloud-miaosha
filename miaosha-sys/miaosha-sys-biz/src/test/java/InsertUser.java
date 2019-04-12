import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaosha.common.core.constants.CommonConstants;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;
import tech.tengshe789.miaosha.common.core.constants.StatusConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.common.security.entity.MiaoshaUser;
import tech.tengshe789.miaosha.common.security.exception.MiaoshaUsernameNotFoundException;
import tech.tengshe789.miaosha.common.security.service.MiaoshaUserDetailsService;
import tech.tengshe789.miaosha.sys.api.dto.UserDTO;
import tech.tengshe789.miaosha.sys.api.dto.UserInfo;
import tech.tengshe789.miaosha.sys.api.entity.SysUser;
import tech.tengshe789.miaosha.sys.api.entity.SysUserRole;
import tech.tengshe789.miaosha.sys.biz.service.SysUserService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: -miaosha
 * @description:
 * @author: tEngSHe789
 * @create: 2019-03-31 21:30
 **/
@AllArgsConstructor
public class InsertUser {

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder =
			PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encode = passwordEncoder.encode("123456").trim();
		System.out.println(encode);
	}


	private static UserDetails getUserDetails(Result<UserInfo> result) {
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
