package tech.tengshe789.miaosha.sys.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;
import tech.tengshe789.miaosha.common.core.constants.ServiceNameConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.common.log.annotation.SysLog;
import tech.tengshe789.miaosha.sys.api.dto.UserDTO;
import tech.tengshe789.miaosha.sys.api.dto.UserInfo;
import tech.tengshe789.miaosha.sys.api.feign.factories.RemoteUserServiceFallbackFactory;

import java.util.List;

/**
 * @program: -miaosha
 * @description: user 服务接口
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
@FeignClient(value = ServiceNameConstants.SYS_SERVICE,fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {
	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     调用标志
	 * @return Result
	 */
	@GetMapping("/user/info/{username}")
	Result<UserInfo> info(@PathVariable("username") String username
		, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 通过社交账号或手机号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @param from  调用标志
	 * @return Result
	 */
	@GetMapping("/social/info/{inStr}")
	Result<UserInfo> social(@PathVariable("inStr") String inStr
		, @RequestHeader(SecurityConstants.FROM) String from);


	@PostMapping("/user/register")
	public Result<Boolean> register(@RequestBody UserDTO userDto);
}
