package com.example.projspringdatajpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/*
 * Configuracion de swager para la documentacion del proyecto
 * HTML: http://localhost:8080/swagger-ui/
 * */
@Configuration
public class SwagerConfig {

    @Bean //bean va a estar disponible en el contenedor de Spring
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetail())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                ;
    }

    private ApiInfo apiDetail() {
        return new ApiInfo("Spring Boot API REST",
                "Library Api Rest Docs", "1.0",
                "http://www.google.com",
                new Contact("David", "http://www.google.com","cdave@gmail.com"),
                "MIT", "http://www.google.com",
                Collections.emptyList());
    }

}
