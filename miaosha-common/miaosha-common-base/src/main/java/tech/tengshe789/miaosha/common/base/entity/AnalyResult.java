package tech.tengshe789.miaosha.common.base.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: -miaosha
 * @description:
 * @author: tEngSHe789
 * @create: 2019-03-19 18:07
 **/
@Data
@Accessors(chain = true)
public class AnalyResult {
	private String info;//分组条件
	private Long count;//总数
}
