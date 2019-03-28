package tech.tengshe789.miaosha.mall.order.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("")
@ApiModel(value = "")
public class OrderInfo extends Model<OrderInfo> {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long deliveryAddrId;//交付地址
    private String goodsName;
    private Integer goodsCount;//数量
    private Double goodsPrice;
    private Integer orderChannel;//渠道
    private Integer status;//状态
    private Date createDate;
    private Date payDate;
}

