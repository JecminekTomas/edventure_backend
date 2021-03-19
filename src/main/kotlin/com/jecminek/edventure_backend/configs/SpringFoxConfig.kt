package com.jecminek.edventure_backend.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import java.util.*


// TODO: 18.03.2021 CASCADE, FETCH TYPES 

@Configuration
class SpringFoxConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
    }
    private fun apiInfo(): ApiInfo {
        return ApiInfo(
            "Edventure REST API",
            "This is documentation of REST API made for bachelor theses made by Tomáš Ječmínek. ",
            "V1",
            "Terms of service",
            Contact("Tomáš Ječmínek", "https://www.linkedin.com/in/tomas-jecminek/", "jecminek.tomas@outlook.com"),
            "License of API", "API license URL", Collections.emptyList()
        )
    }
}