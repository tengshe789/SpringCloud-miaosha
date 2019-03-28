package tech.tengshe789.miaosha.gateway.filter;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import tech.tengshe789.miaosha.common.core.constants.SecurityConstants;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @program: -miaosha
 * @description: 密码解密拦截器http://tool.chacuo.net/cryptaes
 * @author: tEngSHe789
 * @create: 2019-03-25 18:47
 **/
@Slf4j
@Component
public class PasswordDecoderFilter extends AbstractGatewayFilterFactory {
	private static final String PASSWORD = "password";
	private static final String KEY_ALGORITHM = "AES";
	//16位
	@Value("${security.encode.key:1234567812345678}")
	private String encodeKey;

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			URI uri = exchange.getRequest().getURI();

			// 不是登录请求,则走出这个过滤器
			if (!StrUtil.containsAnyIgnoreCase(uri.getPath(), SecurityConstants.OAUTH_TOKEN_URL)) {
				return chain.filter(exchange);
			}

			String queryParam = uri.getRawQuery();
			Map<String, String> paramMap = HttpUtil.decodeParamMap(queryParam, CharsetUtil.UTF_8);

			String password = paramMap.get(PASSWORD);
			if (StrUtil.isNotBlank(password)) {
				try {
					password = decryptAES(password, encodeKey);
				} catch (Exception e) {
					log.error("密码解密失败:{}", password);
					return Mono.error(e);
				}
				paramMap.put(PASSWORD, password.trim());
			}

			URI newUri = UriComponentsBuilder.fromUri(uri)
				.replaceQuery(HttpUtil.toParams(paramMap))
				.build(true)
				.toUri();

			ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(newUri).build();
			return chain.filter(exchange.mutate().request(newRequest).build());
		};
	}

	@SneakyThrows
	private static String decryptAES(String data, String pass) {
		AES aes = new AES(Mode.CBC, Padding.NoPadding,
			new SecretKeySpec(pass.getBytes(), KEY_ALGORITHM),
			new IvParameterSpec(pass.getBytes()));
		byte[] result = aes.decrypt(Base64.decode(data.getBytes(StandardCharsets.UTF_8)));
		return new String(result, StandardCharsets.UTF_8);
	}

}
