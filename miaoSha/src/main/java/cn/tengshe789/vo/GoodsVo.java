package cn.tengshe789.vo;

import cn.tengshe789.domain.Goods;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GoodsVo extends Goods {
    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
