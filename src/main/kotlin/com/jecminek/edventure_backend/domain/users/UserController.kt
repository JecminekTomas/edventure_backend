package com.jecminek.edventure_backend.domain.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
 class UserController {

    @Autowired
    lateinit var service: UserService

    @GetMapping("/users/")
    fun findAll(): MutableIterable<User> = service.findAll()

    @GetMapping("/users/{name}")
    fun findByLastName(@PathVariable name: String): List<User> = service.findByLastName(name)

    @DeleteMapping("/users/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)

    @PostMapping
    fun create(@RequestBody user: User): User = service.save(user)

    @PutMapping("users/{id}")
    fun update(@PathVariable id: Long): User? {
        return service.findByIdOrNull(id)
    }
}