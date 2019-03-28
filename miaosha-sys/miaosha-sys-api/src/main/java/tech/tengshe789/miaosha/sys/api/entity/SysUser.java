package tech.tengshe789.miaosha.sys.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lengleng
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "用户表")
public class SysUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value="",name="",example="")
	@TableId(value = "user_id", type = IdType.AUTO)
	private Long userId;
	/**
	 * 用户名
	 */
	@ApiModelProperty(value="",name="",example="")
	private String username;

	@ApiModelProperty(value="",name="",example="")
	private String password;
	/**
	 * 随机盐
	 */
	@ApiModelProperty(value="",name="",example="")
	@JsonIgnore
	private String salt;
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
	 * 0-正常，1-删除
	 */
	@ApiModelProperty(value="",name="",example="")
	@TableLogic
	private String delFlag;

	/**
	 * 锁定标记
	 */
	@ApiModelProperty(value="",name="",example="")
	private String lockFlag;

	/**
	 * 简介
	 */
	@ApiModelProperty(value="",name="",example="")
	private String phone;
	/**
	 * 头像
	 */
	@ApiModelProperty(value="",name="",example="")
	private String avatar;

	/**
	 * 微信openid
	 */
	@ApiModelProperty(value="",name="",example="")
	private String wxOpenid;

	/**
	 * QQ openid
	 */
	@ApiModelProperty(value="",name="",example="")
	private String qqOpenid;

}
