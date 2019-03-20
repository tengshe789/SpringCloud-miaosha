/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package tech.tengshe789.miaosha.upms.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.common.log.annotation.SysLog;
import tech.tengshe789.miaosha.common.security.annotation.Inner;
import tech.tengshe789.miaosha.upms.entity.SysSocialDetails;
import tech.tengshe789.miaosha.upms.service.SysSocialDetailsService;

import javax.validation.Valid;


/**
 * 系统社交登录账号表
 *
 * @author pigx code generator
 * @date 2018-08-16 21:30:41
 */
@RestController
@RequestMapping("/social")
@AllArgsConstructor
@Api(value = "social", description = "三方账号管理模块")
public class SysSocialDetailsController {
	private final SysSocialDetailsService sysSocialDetailsService;


	/**
	 * 社交登录账户简单分页查询
	 *
	 * @param page             分页对象
	 * @param sysSocialDetails 社交登录
	 * @return
	 */
	@GetMapping("/page")
	public Result getSocialDetailsPage(Page page, SysSocialDetails sysSocialDetails) {
		return Result.success(sysSocialDetailsService.page(page, Wrappers.query(sysSocialDetails)));
	}


	/**
	 * 信息
	 *
	 * @param id
	 * @return Result
	 */
	@GetMapping("/{id}")
	public Result getById(@PathVariable("id") Integer id) {
		return Result.success(sysSocialDetailsService.getById(id));
	}

	/**
	 * 保存
	 *
	 * @param sysSocialDetails
	 * @return Result
	 */
	@SysLog("保存三方信息")
	@PostMapping
	public Result save(@Valid @RequestBody SysSocialDetails sysSocialDetails) {
		return Result.success(sysSocialDetailsService.save(sysSocialDetails));
	}

	/**
	 * 修改
	 *
	 * @param sysSocialDetails
	 * @return Result
	 */
	@SysLog("修改三方信息")
	@PutMapping
	public Result updateById(@Valid @RequestBody SysSocialDetails sysSocialDetails) {
		sysSocialDetailsService.updateById(sysSocialDetails);
		return Result.success(Boolean.TRUE);
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return Result
	 */
	@SysLog("删除三方信息")
	@DeleteMapping("/{id}")
	public Result removeById(@PathVariable Integer id) {
		return Result.success(sysSocialDetailsService.removeById(id));
	}

	/**
	 * 通过社交账号、手机号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @return
	 */
	@Inner
	@GetMapping("/info/{inStr}")
	public Result getUserInfo(@PathVariable String inStr) {
		return Result.success(sysSocialDetailsService.getUserInfo(inStr));
	}

	/**
	 * 绑定社交账号
	 *
	 * @param state 类型
	 * @param code  code
	 * @return
	 */
	@PostMapping("/bind")
	public Result bindSocial(String state, String code) {
		return Result.success(sysSocialDetailsService.bindSocial(state, code));
	}
}
