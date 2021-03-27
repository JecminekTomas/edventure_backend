package com.jecminek.edventure_backend.configs

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


// TODO: 18.03.2021 CASCADE, FETCH TYPES 

@Configuration
class OpenApiConfig {
    @Bean
    fun api(): OpenAPI = OpenAPI().info(apiInfo())

    private fun apiInfo(): Info = Info()
        .title("Edventure REST API")
        .description("This is documentation of REST API made for bachelor theses made by Tomáš Ječmínek. ")
        .version("v0.2.0")
        .contact(
            Contact().name("Tomáš Ječmínek").email("xjecmine@mendelu.cz")
                .url("https://www.linkedin.com/in/tomas-jecminek/")
        )
}