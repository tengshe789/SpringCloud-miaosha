package tech.tengshe789.miaosha.sys.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;
import tech.tengshe789.miaosha.common.core.constants.ServiceNameConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.sys.api.dto.UserInfo;
import tech.tengshe789.miaosha.sys.api.entity.SysUser;

import java.util.List;

/**
 * @program: -miaosha
 * @description: user 服务接口
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
@FeignClient(value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteUserService {
	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     调用标志
	 * @return R
	 */
	@GetMapping("/user/info/{username}")
	Result<UserInfo> info(@PathVariable("username") String username
		, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 通过社交账号或手机号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @param from  调用标志
	 * @return
	 */
	@GetMapping("/social/info/{inStr}")
	Result<UserInfo> social(@PathVariable("inStr") String inStr
		, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 查询上级部门的用户信息
	 *
	 * @param username 用户名
	 * @return R
	 */
	@GetMapping("/user/ancestor/{username}")
	Result<List<SysUser>> ancestorUsers(@PathVariable("username") String username);
}
