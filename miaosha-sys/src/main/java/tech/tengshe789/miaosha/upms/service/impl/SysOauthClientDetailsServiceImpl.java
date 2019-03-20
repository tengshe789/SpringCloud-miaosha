package tech.tengshe789.miaosha.upms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;
import tech.tengshe789.miaosha.upms.entity.SysOauthClientDetails;
import tech.tengshe789.miaosha.upms.mapper.SysOauthClientDetailsMapper;
import tech.tengshe789.miaosha.upms.service.SysOauthClientDetailsService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 */
@Service
public class SysOauthClientDetailsServiceImpl extends ServiceImpl<SysOauthClientDetailsMapper, SysOauthClientDetails> implements SysOauthClientDetailsService {

	/**
	 * 通过ID删除客户端
	 *
	 * @param id value：miaosha_oauth:client:details
	 * @return
	 */
	@Override
	@CacheEvict(value = SecurityConstants.CLIENT_DETAILS_KEY, key = "#id")
	public Boolean removeClientDetailsById(String id) {
		return this.removeById(id);
	}

	/**
	 * 根据客户端信息
	 *
	 * @param clientDetails
	 * @return
	 */
	@Override
	@CacheEvict(value = SecurityConstants.CLIENT_DETAILS_KEY, key = "#clientDetails.clientId")
	public Boolean updateClientDetailsById(SysOauthClientDetails clientDetails) {
		return this.updateById(clientDetails);
	}
}
