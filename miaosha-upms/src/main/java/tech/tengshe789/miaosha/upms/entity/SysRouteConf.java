package tech.tengshe789.miaosha.upms.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 路由
 *
 * @author lengleng
 */
@Data
@TableName("sys_route_conf")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "路由表")
public class SysRouteConf extends Model<SysRouteConf> {
	private static final long serialVersionUID = 1L;

	@JSONField(serialize = false)
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 路由ID
	 */
	@ApiModelProperty(value="",name="",example="")
	private String routeId;
	/**
	 * 路由名称
	 */
	@ApiModelProperty(value="",name="",example="")
	private String routeName;
	/**
	 * 断言
	 */
	@ApiModelProperty(value="",name="",example="")
	private String predicates;
	/**
	 * 过滤器
	 */
	@ApiModelProperty(value="",name="",example="")
	private String filters;
	/**
	 * uri
	 */
	@ApiModelProperty(value="",name="",example="")
	private String uri;
	/**
	 * 排序
	 */
	@ApiModelProperty(value="",name="",example="")
	@TableField(value = "`order`")
	private Integer order;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="",name="",example="")
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value="",name="",example="")
	private LocalDateTime updateTime;
	/**
	 * 删除标识（0-正常,1-删除）
	 */
	@TableLogic
	@ApiModelProperty(value="",name="",example="")
	private String delFlag;

}
