package com.bosko.typeqastassignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        Contact contact = new Contact("Bosko Djokic", "https://github.com/boskodjokic", "boskodjokic@yahoo.com");

        return new ApiInfo(
                "Typeqast Assignment",
                "Project of REST APIs with Spring Boot",
                "1.0",
                "Terms of Service",
                contact,
                "Apache Licence Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0.txt",
                new ArrayList<>()
        );
    }
}
