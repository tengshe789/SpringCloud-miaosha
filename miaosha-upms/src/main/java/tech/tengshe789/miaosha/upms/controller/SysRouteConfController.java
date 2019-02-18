package tech.tengshe789.miaosha.upms.controller;

import cn.hutool.json.JSONArray;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.common.log.annotation.SysLog;
import tech.tengshe789.miaosha.upms.service.SysRouteConfService;


/**
 * 路由
 *
 * @author lengleng
 */
@AllArgsConstructor
@RequestMapping("/route")
@Api(value = "route",description = "动态路由管理模块")
public class SysRouteConfController {
	private final SysRouteConfService sysRouteConfService;


	/**
	 * 获取当前定义的路由信息
	 *
	 * @return
	 */
	@GetMapping
	public Result listRoutes() {
		return Result.success(sysRouteConfService.list());
	}

	/**
	 * 修改路由
	 *
	 * @param routes 路由定义
	 * @return
	 */
	@SysLog("修改路由")
	@PutMapping
	public Result updateRoutes(@RequestBody JSONArray routes) {
		return Result.success(sysRouteConfService.updateRoutes(routes));
	}

}
