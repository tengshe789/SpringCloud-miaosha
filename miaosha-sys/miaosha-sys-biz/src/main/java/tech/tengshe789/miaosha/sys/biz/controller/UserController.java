package tech.tengshe789.miaosha.sys.biz.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.tengshe789.miaosha.common.core.constants.CodeMsgConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.common.log.annotation.SysLog;
import tech.tengshe789.miaosha.common.security.annotation.Inner;
import tech.tengshe789.miaosha.common.security.utils.SecurityUtils;
import tech.tengshe789.miaosha.sys.api.dto.UserDTO;
import tech.tengshe789.miaosha.sys.api.entity.SysUser;
import tech.tengshe789.miaosha.sys.api.vo.UserVO;
import tech.tengshe789.miaosha.sys.biz.service.SysUserService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lengleng
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(value = "user", description = "用户管理模块")
@Slf4j
public class UserController {
	private final SysUserService userService;

	/**
	 * 获取当前用户全部信息
	 *
	 * @return 用户信息
	 */
	@GetMapping(value = {"/info"})
	public Result info() {
		String username = SecurityUtils.getUser().getUsername();
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
				.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return Result.error(CodeMsgConstants.FAILED_TO_OBTAIN_CURRENT_USER_INFORMATION);
		}
		return Result.success(userService.findUserInfo(user));
	}

	/**
	 * 获取指定用户全部信息
	 *
	 * @return 用户信息
	 */
	@Inner
	@GetMapping("/info/{username}")
	public Result info(@PathVariable String username) {
		log.info(username);
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
				.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			log.error("用户:{}不存在", username);
			return Result.error(CodeMsgConstants.USER_INFORMATION_IS_EMPTY,username);
		}
		return Result.success(userService.findUserInfo(user));
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id ID
	 * @return 用户信息
	 */
	@GetMapping("/{id}")
	public Result user(@PathVariable Long id) {
		return Result.success(userService.selectUserVoById(id));
	}

	/**
	 * 根据用户名查询用户信息
	 *
	 * @param username 用户名
	 * @return
	 */
	@GetMapping("/details/{username}")
	public Result user(@PathVariable String username) {
		SysUser condition = new SysUser();
		condition.setUsername(username);
		return Result.success(userService.getOne(new QueryWrapper<>(condition)));
	}

	/**
	 * 删除用户信息
	 *
	 * @param id ID
	 * @return Result
	 */
	@SysLog("删除用户信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_user_del')")
	@ApiOperation(value = "删除用户", notes = "根据ID删除用户")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path")
	public Result userDel(@PathVariable Integer id) {
		SysUser sysUser = userService.getById(id);
		return Result.success(userService.deleteUserById(sysUser));
	}

	/**
	 * 添加用户
	 *
	 * @param userDto 用户信息
	 * @return success/false
	 */
	@SysLog("添加用户")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_user_add')")
	public Result user(@RequestBody UserDTO userDto) {
		return Result.success(userService.saveUser(userDto));
	}

	/**
	 * 注册
	 *
	 * @param userDto
	 * @return
	 */
	@ApiOperation(value = "注册", notes = "注册")
	@ApiImplicitParam(name = "userDto", value = "用户实体user", required = true, dataType = "UserDto")
	@PostMapping("register")
	@SysLog("注册用户")
	public Result<Boolean> register(@RequestBody UserDTO userDto) {
		String username = userDto.getUsername();
		UserVO userVO = userService.selectUserVoByUsername(username);
		//判断是否有重名的
		if (userVO.getUsername().equals(username)) {
			return Result.error(CodeMsgConstants.REGISTRATION_ERROR);
		}
		return Result.success(userService.saveUser(userDto));
	}

	/**
	 * 更新用户信息
	 *
	 * @param userDto 用户信息
	 * @return Result
	 */
	@SysLog("更新用户信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	public Result updateUser(@Valid @RequestBody UserDTO userDto) {
		return Result.success(userService.updateUser(userDto));
	}

	/**
	 * 分页查询用户
	 *
	 * @param page    参数集
	 * @param userDTO 查询参数列表
	 * @return 用户集合
	 */
	@GetMapping("/page")
	public Result getUserPage(Page page, UserDTO userDTO) {
		return Result.success(userService.getUsersWithRolePage(page, userDTO));
	}

	/**
	 * 修改个人信息
	 *
	 * @param userDto userDto
	 * @return success/false
	 */
	@SysLog("修改个人信息")
	@PutMapping("/edit")
	public Result updateUserInfo(@Valid @RequestBody UserDTO userDto) {
		return userService.updateUserInfo(userDto);
	}

}
