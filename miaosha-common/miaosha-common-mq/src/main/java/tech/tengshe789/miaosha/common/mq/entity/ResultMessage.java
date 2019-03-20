package tech.tengshe789.miaosha.common.mq.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: -miaosha
 * @description:
 * @author: tEngSHe789
 * @create: 2019-03-19 18:09
 **/
@Data
@Accessors(chain = true)
public class ResultMessage {
	private String status;//状态 fail 、 success
	private String message;//消息内容
}
