package tech.tengshe789.miaosha.common.core.swagger;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @program: -miaosha
 * @description: swagger配置
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-02-11 15:09
 **/
@EnableAutoConfiguration
@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration {
	/**
	 * 	默认的排除路径,排除Spring Boot默认的错误处理路径和端点
	 */
	private final static List<String> DEFAULT_EXCLUDE_PATH =
		Arrays.asList("/error","/actuator/**");

	/**
	 * 默认路径
	 */
	private final static String BASE_PATH = "/**";

	/**
	 *  没有bean则将SwaggerProperties放入bean
	 * @return SwaggerProperties
	 */
	@Bean
	@ConditionalOnMissingBean
	public SwaggerProperties swaggerProperties() {
		return new SwaggerProperties();
	}

	/**
	 * Every Docket bean is picked up by the swagger-mvc framework - allowing for multiple
	 * swagger groups i.e. same code base multiple swagger resource listings.
	 */
	@Bean
	public Docket api (SwaggerProperties swaggerProperties) {
		//默认路径
		if (swaggerProperties.getBasePath().isEmpty()) {
			swaggerProperties.getBasePath().add(BASE_PATH);
		}
		List<Predicate<String>> basePath = new ArrayList();
		swaggerProperties.getBasePath().forEach(path -> basePath.add(PathSelectors.ant(path)));
		// 默认排除路径
		if (swaggerProperties.getExcludePath().isEmpty()) {
			swaggerProperties.getExcludePath().addAll(DEFAULT_EXCLUDE_PATH);
		}
		List<Predicate<String>> excludePath = new ArrayList<>();
		swaggerProperties.getExcludePath().forEach(path -> excludePath.add(PathSelectors.ant(path)));

		return new Docket(DocumentationType.SWAGGER_2)
			.host(swaggerProperties.getHost())
			.apiInfo(apiInfo(swaggerProperties))
			.select()
			.apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
			.paths(Predicates.and(Predicates.not(Predicates.or(excludePath)), Predicates.or(basePath)))
			.build()
			.securitySchemes(Collections.singletonList(securitySchema()))
			.securityContexts(Collections.singletonList(securityContext()))
			.pathMapping("/");
	}

	/**
	 * 配置默认的全局鉴权策略的开关，通过正则表达式进行匹配；默认匹配所有URL
	 *
	 * @return
	 */
	private SecurityContext securityContext() {
		return SecurityContext.builder()
			.securityReferences(defaultAuth())
			.forPaths(PathSelectors.regex(swaggerProperties().getAuthorization().getAuthRegex()))
			.build();
	}

	/**
	 * 默认的全局鉴权策略
	 *
	 * @return
	 */
	private List<SecurityReference> defaultAuth() {
		ArrayList<AuthorizationScope> authorizationScopeList = new ArrayList<>();
		swaggerProperties().getAuthorization()
			.getAuthorizationScopeList()
			.forEach(authorizationScope ->
				authorizationScopeList.add(
					new AuthorizationScope(authorizationScope.getScope(), authorizationScope.getDescription())));
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[authorizationScopeList.size()];
		return Collections.singletonList(SecurityReference.builder()
			.reference(swaggerProperties().getAuthorization().getName())
			.scopes(authorizationScopeList.toArray(authorizationScopes))
			.build());
	}


	private OAuth securitySchema() {
		ArrayList<AuthorizationScope> authorizationScopeList = new ArrayList<>();
		swaggerProperties().getAuthorization()
			.getAuthorizationScopeList()
			.forEach(authorizationScope ->
				authorizationScopeList.add(new AuthorizationScope(authorizationScope.getScope(), authorizationScope.getDescription())));
		ArrayList<GrantType> grantTypes = new ArrayList<>();
		swaggerProperties().getAuthorization().getTokenUrlList().forEach(tokenUrl -> grantTypes.add(new ResourceOwnerPasswordCredentialsGrant(tokenUrl)));
		return new OAuth(swaggerProperties().getAuthorization().getName(), authorizationScopeList, grantTypes);
	}

	/**
	 * 构建 api文档的详细信息函数
	 * @param swaggerProperties
	 * @return
	 */
	private ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
		return new ApiInfoBuilder()
			.title(swaggerProperties.getTitle())
			.description(swaggerProperties.getDescription())
			.license(swaggerProperties.getLicense())
			.termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
			.contact(new Contact(swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(), swaggerProperties.getContact().getEmail()))
			.version(swaggerProperties.getVersion())
			.build();
	}
}
