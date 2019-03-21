package tech.tengshe789.miaosha.auth.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.ConvertingCursor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.tengshe789.miaosha.common.core.constants.CodeMsgConstants;
import tech.tengshe789.miaosha.common.core.constants.PaginationConstants;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;
import tech.tengshe789.miaosha.common.core.result.Result;
import tech.tengshe789.miaosha.common.security.annotation.Inner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: -miaosha
 * @description: token端点控制器
 * @author: tEngSHe789
 * @create: 2019-03-21 10:50
 **/
@RestController
@AllArgsConstructor
@RequestMapping("/token")
public class TokenEndpoint {
	private static final String MIAOSHA_OAUTH_ACCESS = SecurityConstants.MIAOSHA_PREFIX+ SecurityConstants.OAUTH_PREFIX + "auth_to_access:";

	private final TokenStore tokenStore;
	private final RedisTemplate redisTemplate;
	private final CacheManager cacheManager;

	/**
	 * 认证页面
	 *
	 * @return ModelAndView
	 */
	@GetMapping("/login")
	public ModelAndView require() {
		return new ModelAndView("login");
	}

	/**
	 * 退出token
	 *
	 * @param authHeader Authorization
	 */
	@DeleteMapping("/logout")
	public Result logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
		//请求头没有则返回错误
		if (StrUtil.isBlank(authHeader)) {
			return Result.error(CodeMsgConstants.EXIT_FAILED_TOKEN_EMPTY);
		}
		//将请求头的BEARER token 标记换成空
		String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, StrUtil.EMPTY).trim();
		//读取AccessToken
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
		if (accessToken == null || StrUtil.isBlank(accessToken.getValue())) {
			return Result.error(CodeMsgConstants.EXIT_FAILED_TOKEN_INVALID);
		}
		//通过AccessToken读取认证信息
		OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);
		//根据认证信息的名删除缓存
		cacheManager.getCache("user_details")
			.evict(auth2Authentication.getName());
		tokenStore.removeAccessToken(accessToken);
		return Result.success(true);
	}

	/**
	 * 令牌管理调用
	 *
	 * @param token token
	 * @return
	 */
	@Inner
	@DeleteMapping("/{token}")
	public Result<Boolean> delToken(@PathVariable("token") String token) {
		OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
		tokenStore.removeAccessToken(oAuth2AccessToken);
		return Result.success(Boolean.TRUE);
	}


	/**
	 * 查询token
	 *
	 * @param params 分页参数
	 * @return
	 */
	@Inner
	@PostMapping("/page")
	public Result<Page> tokenList(@RequestBody Map<String, Object> params) {
		//根据分页参数获取对应数据
		String key = String.format("%s*:%s", MIAOSHA_OAUTH_ACCESS);
		List<String> pages = findKeysForPage(key, MapUtil.getInt(params, PaginationConstants.CURRENT)
			, MapUtil.getInt(params, PaginationConstants.SIZE));

		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		Page result = new Page(MapUtil.getInt(params, PaginationConstants.CURRENT), MapUtil.getInt(params, PaginationConstants.SIZE));
		result.setRecords(redisTemplate.opsForValue().multiGet(pages));
		result.setTotal(Long.valueOf(redisTemplate.keys(key).size()));
		return new Result<>(result);
	}


	private List<String> findKeysForPage(String patternKey, int pageNum, int pageSize) {
		ScanOptions options = ScanOptions.scanOptions().match(patternKey).build();
		RedisSerializer<String> redisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
		Cursor cursor = (Cursor) redisTemplate.executeWithStickyConnection(redisConnection -> new ConvertingCursor<>(redisConnection.scan(options), redisSerializer::deserialize));
		List<String> result = new ArrayList<>();
		int tmpIndex = 0;
		int startIndex = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		assert cursor != null;
		while (cursor.hasNext()) {
			if (tmpIndex >= startIndex && tmpIndex < end) {
				result.add(cursor.next().toString());
				tmpIndex++;
				continue;
			}
			if (tmpIndex >= end) {
				break;
			}
			tmpIndex++;
			cursor.next();
		}
		return result;
	}
}

