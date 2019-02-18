package tech.tengshe789.miaosha.common.security.utils;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;
import tech.tengshe789.miaosha.common.security.entity.MiaoshaUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: -miaosha
 * @description: 安全工具类
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-12 12:02
 **/
@UtilityClass
public class SecurityUtils {
	/**
	 * 拿到Authentication
	 * @return Authentication
	 */
	public Authentication getAuthentication () {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取用户
	 * @param authentication
	 * @return MiaoshaUser
	 */
	public MiaoshaUser getUser (Authentication authentication) {
		Object principal = authentication.getPrincipal();
		return (principal instanceof MiaoshaUser) ? (MiaoshaUser)principal : null;
	}

	/**
	 * 获取用户
	 * @return MiaoshaUser
	 */
	public MiaoshaUser getUser () {
		Authentication authentication = getAuthentication();
		return (authentication != null) ? getUser(authentication) : null;
	}

	/**
	 * 获取当前用名
	 *
	 * @return String
	 */
	public String getUsername (Authentication authentication) {
		Object principal = authentication.getPrincipal();
		return (principal instanceof String) ? principal.toString() : null;
	}

	/**
	 * 获取当前用名
	 *
	 * @return String
	 */
	public String getUsername () {
		Authentication authentication = getAuthentication();
		Object principal = authentication.getPrincipal();
		return (authentication != null) ? getUsername(authentication) : null;
	}

	/**
	 * 获取用户role信息
	 * @return list
	 */
	public List<Integer> getRoles () {
		Authentication authentication = getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<Integer> roleId = new ArrayList<>();
		//将授权列表中的role拿出来
		authorities.stream()
			.filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE))
			.forEach(granted -> 
				roleId.add(Integer.parseInt(StrUtil.removePrefix(granted.getAuthority(),SecurityConstants.ROLE))));
		return roleId;
	}

}
