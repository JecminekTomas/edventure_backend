package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.domain.user.request.ChangePasswordRequest
import com.jecminek.edventure_backend.domain.user.request.LoginRequest
import com.jecminek.edventure_backend.domain.user.request.RegisterRequest
import com.jecminek.edventure_backend.domain.user.request.UpdateProfileRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirements
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "User", description = "User controller")
class UserController {

    @Autowired
    lateinit var userService: UserService

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
    fun findById(
        @Parameter(
            description = "Id of user to be found",
            example = "1"
        ) @PathVariable id: Long
    ): UserResponse =
        userService.findById(id).convertEntityToResponse()

    @Operation(summary = "Register user")
    @SecurityRequirements
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
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody registerRequest: RegisterRequest): UserResponse = userService.register(registerRequest)

    @Operation(summary = "Login user")
    @SecurityRequirements
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = TokenResponse::class)))
                ]
            ),
            ApiResponse(responseCode = "400")
        ]
    )
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<TokenResponse> = userService.login(loginRequest)

    @PutMapping("/change_password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun changePassword(
        @RequestBody changePasswordRequest: ChangePasswordRequest
    ) =
        userService.changePassword(changePasswordRequest)

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

    @PutMapping("/profile")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @RequestBody updateProfileRequest: UpdateProfileRequest
    ): TokenResponse =
        userService.updateProfile(updateProfileRequest)

/*
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
        userService.delete(id)*/
}