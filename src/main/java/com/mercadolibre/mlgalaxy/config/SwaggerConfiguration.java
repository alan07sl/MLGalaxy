package com.mercadolibre.mlgalaxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration class.
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    /**
     * Generates and builds the Docket bean.
     *
     * @return {@link Docket}
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.mercadolibre.mlgalaxy.controller"))
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Generates and builds the API information which describes the API.
     *
     * @return {@link ApiInfo}
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Galaxy weather forecast API")
                .description("Created by Alan Hryniewicz")
                .version("1.0")
                .termsOfServiceUrl("http://alan07sl.github.io")
                .build();
    }


}