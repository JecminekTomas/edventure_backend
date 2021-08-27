package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.domain.review.ReviewService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "Users", description = "User might be teacher, student or both.")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var reviewService: ReviewService

    @Operation(summary = "Find user by id")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = UserResponse::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@Parameter(description = "Id of user to be found", example = "1") @PathVariable id: Long): UserResponse =
        userService.findById(id)

    @Operation(summary = "Create user")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = UserResponse::class)))
                ]
            ),
            ApiResponse(responseCode = "400")
        ]
    )
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody userRequest: UserRequest): UserResponse = userService.create(userRequest)

    @Operation(summary = "Update user")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "202",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = UserResponse::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @Parameter(description = "ID of updated user", example = "1") @PathVariable id: Long,
        @RequestBody userRequest: UserRequest
    ): UserResponse =
        userService.update(id, userRequest)

    @Operation(summary = "Delete user")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204"),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@Parameter(description = "ID of updated user", example = "1") @PathVariable id: Long) =
        userService.delete(id)

}