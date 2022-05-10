package com.example.backend.config;

import com.google.common.collect.Lists;
import io.swagger.models.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket postsApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()));
       docket = docket.select()
                .apis(RequestHandlerSelectors.basePackage("com.example.backend.api"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
        return docket;
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "HRM",
                "Human resources management",
                "Version 1.0",
                "Terms of service",
                null,
                "License of API", "API license URL", Collections.emptyList());
    }
    // @formatter:on

    private ApiKey apiKey() {
        return new ApiKey("JWTT", "Authorization", "headerr");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
    }
}
