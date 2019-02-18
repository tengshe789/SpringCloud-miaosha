package tech.tengshe789.miaosha.common.core.util;

import com.alibaba.fastjson.JSON;
import lombok.experimental.UtilityClass;

/**
 * @program: miaosha
 * @description: 序列化工具
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-07 15:40
 **/
@UtilityClass
public class JsonUtil {
	/**
	 * 对象转json
	 * @param bean
	 * @param <T>
	 * @return
	 */
	public static <T> String beanToString (T bean) {
		if(bean == null) {
			return null;
		}
		Class<?> clazz = bean.getClass();
		if(clazz == int.class || clazz == Integer.class) {
			return ""+bean;
		}else if(clazz == String.class) {
			return (String)bean;
		}else if(clazz == long.class || clazz == Long.class) {
			return ""+bean;
		}else {
			return JSON.toJSONString(bean);
		}
	}

	/**
	 *  json 转 对象
	 * @param json
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T stringToBean (String json ,Class<T> clazz) {
		if(json == null || json.length() <= 0 || clazz == null) {
			return null;
		}
		if(clazz == int.class || clazz == Integer.class) {
			return (T)Integer.valueOf(json);
		}else if(clazz == String.class) {
			return (T)json;
		}else if(clazz == long.class || clazz == Long.class) {
			return  (T)Long.valueOf(json);
		}else {
			return JSON.toJavaObject(JSON.parseObject(json), clazz);
		}
	}
}
