package tech.tengshe789.miaosha.discovery.controller;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.Check;
import com.ecwid.consul.v1.health.model.HealthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: miaosha
 * @description: Consul功能扩展器
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-07 11:10
 **/
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/discovery")
public class ConsulController {

	private final DiscoveryClient discoveryClient;

	private final ConsulClient consulClient;

	/**
	 * 拿到服务实例
	 * @return instance
	 */
	@GetMapping("/getInstance")
	public Map<String, List<ServiceInstance>> getInstance(){
		HashMap<String, List<ServiceInstance>> instance = new HashMap<>(16);
		List<String> services = discoveryClient.getServices();
		services.parallelStream().forEach(service -> {
			List<ServiceInstance> serviceInstance = discoveryClient.getInstances(service);
			instance.put(service,serviceInstance);
		});
		return instance;
	}

	/**
	 * 删除服务（前端需要将参数名进行绑定）
	 * @param serviceName 服务名
	 * @return 删除One服务成功
	 */
	@DeleteMapping("/deleteService/{serviceName}")
	public String deleteOneService(@PathVariable("serviceName") String serviceName) {
		deleteService(serviceName);
		return "删除One服务成功！";
	}

	/**
	 * 删除所有服务（前端需要将参数名进行绑定）
	 * @return 删除All服务成功
	 */
	@DeleteMapping("/deleteAllService")
	public String deleteAllService(){
		consulClient.getAgentChecks().getValue().forEach((k,v)->{
			deleteService(v.getServiceName());
		});
		log.info("删除All服务成功！");
		return "删除All服务成功！";
	}

	public void deleteService(String serviceName) {
		//拿到健康服务的心跳
		List<HealthService> response =
			consulClient.getHealthServices(serviceName, false, null).getValue();

		response.forEach(service ->{
			//创建一个consul agent来存放无效服务
			ConsulClient notGoodConsulClient = new ConsulClient(service.getNode().getAddress());
			service.getChecks().forEach(check -> {
				//判断服务check状态
				if (check.getStatus() != Check.CheckStatus.PASSING) {
					log.info("剔除服务{}",check.getServiceId());
					//剔除服务
					notGoodConsulClient.agentCheckDeregister(check.getServiceId());
				}
			});

		});
	}
}
