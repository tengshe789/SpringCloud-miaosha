package tech.tengshe789.miaosha.upms.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.common.log.annotation.SysLog;
import tech.tengshe789.miaosha.upms.feign.RemoteTokenService;

import java.util.Map;

/**
 * @author lengleng
 * getTokenPage 管理
 */
@RestController
@AllArgsConstructor
@RequestMapping("/token")
@Api(value = "token", description = "令牌管理模块")
public class TokenController {
	private final RemoteTokenService remoteTokenService;

	/**
	 * 分页token 信息
	 *
	 * @param params 参数集
	 * @return token集合
	 */
	@GetMapping("/page")
	public Result getTokenPage(@RequestParam Map<String, Object> params) {
		return remoteTokenService.getTokenPage(params, SecurityConstants.FROM_IN);
	}

	/**
	 * 删除
	 *
	 * @param token getTokenPage
	 * @return success/false
	 */
	@SysLog("删除用户token")
	@DeleteMapping("/{token}")
	@PreAuthorize("@pms.hasPermission('sys_token_del')")
	public Result removeById(@PathVariable String token) {
		return remoteTokenService.removeTokenById(token, SecurityConstants.FROM_IN);
	}
}
