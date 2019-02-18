package tech.tengshe789.miaosha.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaosha.common.security.utils.SecurityUtils;
import tech.tengshe789.miaosha.upms.dto.UserInfo;
import tech.tengshe789.miaosha.upms.entity.SysSocialDetails;
import tech.tengshe789.miaosha.upms.entity.SysUser;
import tech.tengshe789.miaosha.upms.handler.LoginHandler;
import tech.tengshe789.miaosha.upms.mapper.SysSocialDetailsMapper;
import tech.tengshe789.miaosha.upms.mapper.SysUserMapper;
import tech.tengshe789.miaosha.upms.service.SysSocialDetailsService;

import java.util.Map;

/**
 * @author <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 */
@Slf4j
@AllArgsConstructor
@Service("sysSocialDetailsService")
public class SysSocialDetailsServiceImpl extends ServiceImpl<SysSocialDetailsMapper, SysSocialDetails> implements SysSocialDetailsService {
	private final Map<String, LoginHandler> loginHandlerMap;
	private final CacheManager cacheManager;
	private final SysUserMapper sysUserMapper;

	/**
	 * 绑定社交账号
	 *
	 * @param type type
	 * @param code code
	 * @return
	 */
	@Override
	public Boolean bindSocial(String type, String code) {
		String identify = loginHandlerMap.get(type).identify(code);
		SysUser sysUser = sysUserMapper.selectById(SecurityUtils.getUser().getId());
		sysUser.setWxOpenid(identify);
		sysUserMapper.updateById(sysUser);
		//更新緩存
		cacheManager.getCache("user_details").evict(sysUser.getUsername());
		return Boolean.TRUE;
	}

	/**
	 * 根据入参查询用户信息
	 *
	 * @param inStr TYPE@code
	 * @return
	 */
	@Override
	public UserInfo getUserInfo(String inStr) {
		String[] inStrs = inStr.split("@");
		String type = inStrs[0];
		String loginStr = inStrs[1];
		return loginHandlerMap.get(type).handle(loginStr);
	}
}
