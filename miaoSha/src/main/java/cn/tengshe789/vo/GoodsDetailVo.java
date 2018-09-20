package cn.tengshe789.vo;

import cn.tengshe789.domain.MiaoshaUser;
import lombok.Getter;
import lombok.Setter;

public class GoodsDetailVo {
    @Getter
    @Setter
    private int miaoshaStatus = 0;
    @Getter
    @Setter
    private int remainSeconds = 0;
    @Getter
    @Setter
    private GoodsVo goods ;
    @Getter
    @Setter
    private MiaoshaUser user;

}

