package cn.tengshe789.rabbitmq;

import cn.tengshe789.domain.MiaoshaUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MiaoshaMessage {
    private MiaoshaUser user;
    private long goodsId;
}
