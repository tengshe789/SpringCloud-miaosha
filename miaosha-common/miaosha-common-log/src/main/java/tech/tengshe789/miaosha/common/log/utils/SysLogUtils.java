package tech.tengshe789.miaosha.common.log.utils;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tech.tengshe789.miaosha.common.core.constants.StatusConstants;
import tech.tengshe789.miaosha.common.log.entity.SysLog;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @program: -miaosha
 * @description: 系统日志工具类
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-12 16:05
 **/
@UtilityClass
public class SysLogUtils {

	public SysLog getSysLog() {
		HttpServletRequest request = ((ServletRequestAttributes) Objects
			.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		SysLog sysLog = new SysLog();
		sysLog.setCreateBy(Objects.requireNonNull(getUsername()));
		sysLog.setType(StatusConstants.STATUS_NORMAL);
		sysLog.setRemoteAddr(HttpUtil.getClientIP(request));
		sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
		sysLog.setMethod(request.getMethod());
		sysLog.setUserAgent(request.getHeader("user-agent"));
		sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
		sysLog.setServiceId(getClientId());
		return sysLog;
	}

	/**
	 * 获取客户端
	 *
	 * @return clientId
	 */
	private static String getClientId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//判断认证是否是oauth2类型
		return (authentication instanceof OAuth2Authentication)
			?((OAuth2Authentication)authentication).getOAuth2Request().getClientId() : null;
	}

	/**
	 * 获取用户名称
	 *
	 * @return username
	 */
	private static String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (authentication == null)? null:authentication.getName();
	}
}
