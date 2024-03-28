package com.api.PersonsCRUD.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java 18 and Spring Boot 3")
                        .version("v1")
                        .description("Curso UDEMY")
                        .termsOfService("http://www.apache.org/licenses/LICENSE-2.0")
                        .license(
                                new io.swagger.v3.oas.models.info.License()
                                        .name("Apache 2.0")
                                        .url("http://www.apache.org/licenses/LICENSE-2.0")
                        )
                );
    }
}