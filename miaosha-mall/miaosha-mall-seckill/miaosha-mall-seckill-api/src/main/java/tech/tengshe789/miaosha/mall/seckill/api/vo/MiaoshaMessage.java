package tech.tengshe789.miaosha.mall.seckill.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import tech.tengshe789.miaosha.sys.api.entity.SysUser;

/**
 * @program: -miaosha
 * @author: tEngSHe789
 * @create: 2019-03-28 15:10
 **/
@Data
@Accessors(chain = true)
public class MiaoshaMessage {
	private long goodsId;

	private SysUser user;
}
