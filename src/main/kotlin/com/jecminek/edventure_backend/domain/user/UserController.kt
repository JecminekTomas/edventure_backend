package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.domain.review.ReviewService
import com.jecminek.edventure_backend.domain.subject.SubjectService
import com.jecminek.edventure_backend.enums.UserRole
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

    @Autowired
    lateinit var subjectService: SubjectService

    /*@GetMapping("/users/subjects")
    fun findTeachersBySubject(
        @Parameter(
            description = "University, faculty belongs to",
            example = "MENDELU"
        ) @RequestParam(required = false, defaultValue = "UNIVERSITY") university: University,
        @Parameter(
            description = "Faculty, where subject is being taught",
            example = "PEF"
        )
        @RequestParam(required = false, defaultValue = "FACULTY") faculty: Faculty,
        @Parameter(
            description = "Title of subject",
            example = "Programovací techniky"
        )
        @RequestParam(required = false) title: String,
        @Parameter(
            description = "Subject code",
            example = "PTN"
        )
        @RequestParam(required = false) code: String,
        page: Int
    ): List<UserDto> = when {
        university != University.UNIVERSITY && faculty != Faculty.FACULTY && title.isNotBlank() && code.isBlank() -> {
            userService.findTeachersBySubjectTitle(university, faculty, title, page).map {
                UserDto(
                    id = it.id,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    email = it.email,
                    biography = it.biography,
                    phoneNumber = it.phoneNumber,
                    roles = it.roles
                )
            }.content
        }
        university != University.UNIVERSITY && faculty == Faculty.FACULTY && title.isBlank() && code.isBlank() -> {
            listOf(userService.findUsersBySubjectCode(code).convertEntityToDto())
        }
        university == University.UNIVERSITY && faculty == Faculty.FACULTY && title.isBlank() && code.isBlank() -> {userService.findAll().map{
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
        }
        else -> throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Bad request"
        )
    }*/

    /*{
    if(university != University.UNIVERSITY && faculty != Faculty.FACULTY && title.isNotBlank())
    return userService.findTeachersBySubjectTitle(university, faculty, title, page).map {
        UserDto(
            id = it.id,
            firstName = it.firstName,
            lastName = it.lastName,
            email = it.email,
            biography = it.biography,
            phoneNumber = it.phoneNumber,
            roles = it.roles
        )
    }.content
    else {
        throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Bad request"
        )
    }*/

    @Operation(summary = "Find user by id")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = UserDto::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@Parameter(description = "Id of user to be found", example = "1") @PathVariable id: Long): UserDto =
        userService.findById(id)

    @Operation(summary = "Creation of user")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = UserDto::class)))
                ]
            ),
            ApiResponse(responseCode = "400")
        ]
    )
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody user: UserDto): UserDto = userService.create(user)

    @Operation(summary = "Update user")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "202",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = UserDto::class)))
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
        @RequestBody user: UserDto
    ): UserDto =
        userService.update(id, user)

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