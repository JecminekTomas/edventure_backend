package com.jecminek.edventure_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
class EdventureBackendApplication

fun main(args: Array<String>) {
    runApplication<EdventureBackendApplication>(*args)
}
