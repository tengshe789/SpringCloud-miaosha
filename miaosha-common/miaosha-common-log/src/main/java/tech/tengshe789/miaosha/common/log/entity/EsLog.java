package tech.tengshe789.miaosha.common.log.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: -miaosha
 * @description: es文档实体类
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-12 15:37
 **/
@Data
//@Document(indexName = "log", type = "log", shards = 1, replicas = 0, refreshInterval = "-1")
public class EsLog implements Serializable {
	private static final long serialVersionUID = 1L;

}

