package tech.tengshe789.miaosha.common.base.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: -miaosha
 * @description:
 * @author: tEngSHe789
 * @create: 2019-03-19 18:07
 **/
@Data
@Accessors(chain = true)
public class ViewResultAnaly {
	private List<String> infolist;//分组list，x轴的值
	private List<Long> countlist;//数量list
	private String result;
	private String typename;//标签类型名称
	private String lablevalue;//标签类型对应的值
}
