package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.enums.UserRole
import io.swagger.annotations.ApiModelProperty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    @Autowired
    lateinit var service: UserService

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    fun findUserByRole(@RequestParam(required = true) role: UserRole): List<User>? = service.findUserByRole(role)

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: Long) = service.findById(id)

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody user: UserDto): UserDto = service.create(user)

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(@PathVariable id: Long, @RequestBody user: UserDto) = service.update(id, user)

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)



}