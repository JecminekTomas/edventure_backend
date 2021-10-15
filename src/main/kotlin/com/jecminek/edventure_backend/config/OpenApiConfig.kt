package com.jecminek.edventure_backend.config

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
class OpenApiConfig {
    @Bean
    fun api(): OpenAPI =
        OpenAPI().info(apiInfo()).addSecurityItem(SecurityRequirement().addList("bearerAuth", listOf("read", "write")))

    private fun apiInfo(): Info = Info()
        .title("Edventure REST API")
        .description("This is documentation of REST API made for bachelor theses made by Tomáš Ječmínek. ")
        .version("v0.6.0")
        .contact(
            Contact().name("Tomáš Ječmínek").email("xjecmine@mendelu.cz")
                .url("https://www.linkedin.com/in/tomas-jecminek/")
        )
}