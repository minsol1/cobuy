package com.cobuy.BE.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
//        info = @Info(
//                title = "SSAFY VUE API 명세서",
//                description = "<h3>SSAFY API Reference for Developers</h3>Swagger를 이용한 VUE API<br><img src=\"/vue/img/ssafy_logo.png\" width=\"150\">",
//                version = "v1",
//                contact = @Contact(
//                        name = "hissam",
//                        email = "hissam@ssafy.com",
//                        url = "http://edu.ssafy.com"
//                )
//        )
)
@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder().group("all").pathsToMatch("/**").build();
    }

    @Bean
    public GroupedOpenApi boardApi() {
        return GroupedOpenApi.builder().group("board").pathsToMatch("/board/**").build();
    }

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder().group("auth").pathsToMatch("/auth/**").build();
    }

}
