package com.jecminek.edventure_backend.domain.university


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
class UniversityController {

    @RestController
    @Tag(name = "University", description = "Admin will be able to create university")
    class UniversityController {
        @Autowired
        lateinit var universityService: UniversityService

        @Operation(summary = "Find all universities")
        @ApiResponses(
            value = [
                ApiResponse(
                    responseCode = "200",
                    content = [
                        (Content(mediaType = "application/json", schema = Schema(implementation = UniversityDTO::class)))
                    ]
                ),
                ApiResponse(responseCode = "400", content = []),
                ApiResponse(responseCode = "404", content = [])
            ]
        )
        @GetMapping("/universities")
        @ResponseStatus(HttpStatus.OK)
        fun findAll(): List<UniversityDTO> =
            universityService.findAll()

        @Operation(summary = "Find university by id")
        @ApiResponses(
            value = [
                ApiResponse(
                    responseCode = "200",
                    content = [
                        (Content(mediaType = "application/json", schema = Schema(implementation = UniversityDTO::class)))
                    ]
                ),
                ApiResponse(responseCode = "400"),
                ApiResponse(responseCode = "404")
            ]
        )
        @GetMapping("/universities/{id}")
        @ResponseStatus(HttpStatus.OK)
        fun findById(
            @Parameter(
                description = "Id of university to be found",
                example = "1"
            ) @PathVariable id: Long
        ): UniversityDTO =
            universityService.getById(id)

        @Operation(summary = "Create university")
        @ApiResponses(
            value = [
                ApiResponse(
                    responseCode = "201",
                    content = [
                        (Content(mediaType = "application/json", schema = Schema(implementation = UniversityDTO::class)))
                    ]
                ),
                ApiResponse(responseCode = "400")
            ]
        )

        @PostMapping("/universities")
        @ResponseStatus(HttpStatus.CREATED)
        fun create(@RequestBody universityDTO: UniversityDTO): UniversityDTO =
            universityService.create(universityDTO)

        @Operation(summary = "Update university")
        @ApiResponses(
            value = [
                ApiResponse(
                    responseCode = "202",
                    content = [
                        (Content(mediaType = "application/json", schema = Schema(implementation = UniversityDTO::class)))
                    ]
                ),
                ApiResponse(responseCode = "400"),
                ApiResponse(responseCode = "404")
            ]
        )
        @PutMapping("/universities/{id}")
        @ResponseStatus(HttpStatus.ACCEPTED)
        fun update(
            @PathVariable id: Long,
            @RequestBody universityDTO: UniversityDTO
        ): UniversityDTO = universityService.update(id, universityDTO)

        @Operation(summary = "Delete university")
        @ApiResponses(
            value = [
                ApiResponse(responseCode = "204"),
                ApiResponse(responseCode = "400"),
                ApiResponse(responseCode = "404")
            ]
        )
        @DeleteMapping("/universities/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        fun delete(@PathVariable id: Long) = universityService.delete(id)
    }
}