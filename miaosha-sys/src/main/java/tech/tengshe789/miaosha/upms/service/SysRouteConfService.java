package tech.tengshe789.miaosha.upms.service;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import reactor.core.publisher.Mono;
import tech.tengshe789.miaosha.upms.entity.SysRouteConf;

import java.util.List;

/**
 * 路由
 *
 * @author lengleng
 */
public interface SysRouteConfService extends IService<SysRouteConf> {

	/**
	 * 获取全部路由
	 * <p>
	 * RedisRouteDefinitionWriter.java
	 * PropertiesRouteDefinitionLocator.java
	 *
	 * @return
	 */
	List<SysRouteConf> routes();

	/**
	 * 更新路由信息
	 *
	 * @param routes 路由信息(类型 JSON数组 )
	 *                * JSON数组是表示中括号括住的数据表现形式<br>
	 *                * 对应的JSON字符串格格式例如:
					 *   <pre>
					 *   ["a", "b", "c", 12]
					 *   </pre>
	 * @return
	 */
	Mono<Void> updateRoutes(JSONArray routes);
}

