package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.enums.UserRole
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    @Autowired
    lateinit var service: UserService

    @Operation(summary = "Get all users with certain role.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Users Found", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = User::class)))))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Did not find any Users", content = [Content()])]
    )
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