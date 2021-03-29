package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.enums.UserRole
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
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

    @Operation(summary = "Find all users with certain role")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "User(s) found", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = UserDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "User(s) With This Role Not Found", content = [Content()])]
    )
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    fun findUserByRole(
        @Parameter(
            description = "Role of user to be found",
            example = "TEACHER"
        ) @RequestParam(required = true) role: UserRole
    ): List<UserDto> = service.findUserByRole(role).map {
        UserDto(
            id = it.id,
            firstName = it.firstName,
            lastName = it.lastName,
            email = it.email,
            biography = it.biography,
            phoneNumber = it.phoneNumber,
            roles = it.roles
        )
    }

    @Operation(summary = "Find user by id")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "User found", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = UserDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Users with this role not found", content = [Content()])]
    )
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@Parameter(description = "Id of user to be found", example = "1") @PathVariable id: Long): UserDto =
        service.findById(id).convertEntityToDto()

    @Operation(summary = "Creation of user")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201", description = "User created", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = UserDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()])]
    )
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody user: UserDto): UserDto = service.create(user).convertEntityToDto()

    @Operation(summary = "Update user")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "202", description = "User updated", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = UserDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "User with this ID not found", content = [Content()])]
    )
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @Parameter(description = "ID of updated user", example = "1") @PathVariable id: Long,
        @RequestBody user: UserDto
    ): UserDto = service.update(id, user).convertEntityToDto()

    @Operation(summary = "Delete user")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "User deleted", content = [Content()]),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "User with this ID not found", content = [Content()])]
    )
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@Parameter(description = "ID of updated user", example = "1") @PathVariable id: Long) =
        service.delete(id)


}