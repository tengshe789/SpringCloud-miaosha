package cn.tengshe789.Swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Parameter;

/**
 * @program: miaoSha
 * @description: Swagger2
 * @author: tEngSHe789
 **/

@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		tokenPar.name("token").description("令牌")
			.modelRef(new ModelRef("string")).parameterType("query").required(false).build();
		pars.add(tokenPar.build());
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("cn.tengshe789"))
			.paths(PathSelectors.any())
			.build().globalOperationParameters(pars);
	}

	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("个人测试")
			.description("个人测试用api")
			.termsOfServiceUrl("https://blog.tengshe789.tech/")
			.contact("测试")
			.version("1.0")
			.build();
	}

}

