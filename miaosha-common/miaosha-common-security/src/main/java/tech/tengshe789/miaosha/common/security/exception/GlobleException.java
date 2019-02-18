package tech.tengshe789.miaosha.common.security.exception;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import tech.tengshe789.miaosha.common.core.constants.CodeMsgConstants;

/**
 * @program: -miaosha
 * @description: 全局异常处理
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-12 10:54
 **/
@Getter
@AllArgsConstructor
public class GlobleException {
	private CodeMsgConstants codeMsgConstants;
}
