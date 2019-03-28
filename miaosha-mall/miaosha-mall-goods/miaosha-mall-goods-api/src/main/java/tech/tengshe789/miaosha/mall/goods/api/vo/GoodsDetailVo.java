package tech.tengshe789.miaosha.mall.goods.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import tech.tengshe789.miaosha.sys.api.entity.SysUser;


@Data
@Accessors(chain = true)
public class GoodsDetailVo {
    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private GoodsVo goods ;
    private SysUser user;
}

