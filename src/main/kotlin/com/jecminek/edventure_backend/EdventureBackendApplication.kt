package com.jecminek.edventure_backend

import com.jecminek.edventure_backend.domain.users.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class EdventureBackendApplication {
    private val log: Logger = LoggerFactory.getLogger(EdventureBackendApplication::class.java)

    fun main(args: Array<String>) {
        runApplication<EdventureBackendApplication>(*args)
    }

    @Bean
    fun init(repository: UserRepository) {
        repository.save(User("Karel", "Janeček","dzany@janeckuj.cz"))
        repository.save(User("Přemysl", "Bakala", "dobrejtypek@seznam.cz"))

        log.info("All users found with findAll():");
        log.info("-------------------------------");

        for (user in repository.findAll()) {
            log.info(user.lastName)
        }
        log.info("")

        log.info("User with ID = 1")
        val userOne = repository.findByIdOrNull(1)
        log.info(userOne?.lastName)

        log.info("-------------------------------");
        log.info("Update user with ID = 1")
        




    }
}


