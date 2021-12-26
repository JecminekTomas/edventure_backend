package com.jecminek.edventure_backend.domain.faculty

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
class FacultyController {

    @RestController
    @Tag(name = "Faculty", description = "Admin will be able to create faculty")
    class FacultyController {
        @Autowired
        lateinit var facultyService: FacultyService

        @Operation(summary = "Find all faculties by parameters")
        @ApiResponses(
            value = [
                ApiResponse(
                    responseCode = "200",
                    content = [
                        (Content(mediaType = "application/json", schema = Schema(implementation = FacultyDTO::class)))
                    ]
                ),
                ApiResponse(responseCode = "400"),
                ApiResponse(responseCode = "404")
            ]
        )
        @GetMapping("/api/faculties")
        @ResponseStatus(HttpStatus.OK)
        fun findById(
            @Parameter(
                description = "Id of university where faculties belongs to.",
                example = "1"
            ) @RequestParam universityId: Long?
        ): List<FacultyDTO> =
            facultyService.findAll(universityId)

        @Operation(summary = "Find faculty by id")
        @ApiResponses(
            value = [
                ApiResponse(
                    responseCode = "200",
                    content = [
                        (Content(mediaType = "application/json", schema = Schema(implementation = FacultyDTO::class)))
                    ]
                ),
                ApiResponse(responseCode = "400"),
                ApiResponse(responseCode = "404")
            ]
        )
        @GetMapping("/api/faculties/{id}")
        @ResponseStatus(HttpStatus.OK)
        fun findById(
            @Parameter(
                description = "Id of faculty to be found",
                example = "1"
            ) @PathVariable id: Long
        ): FacultyDTO =
            facultyService.getById(id)

        @Operation(summary = "Create faculty")
        @ApiResponses(
            value = [
                ApiResponse(
                    responseCode = "201",
                    content = [
                        (Content(mediaType = "application/json", schema = Schema(implementation = FacultyDTO::class)))
                    ]
                ),
                ApiResponse(responseCode = "400")
            ]
        )

        @PostMapping("/api/faculties")
        @ResponseStatus(HttpStatus.CREATED)
        fun create(@RequestBody facultyDTO: FacultyDTO): FacultyDTO =
            facultyService.create(facultyDTO)

        @Operation(summary = "Update faculty")
        @ApiResponses(
            value = [
                ApiResponse(
                    responseCode = "202",
                    content = [
                        (Content(mediaType = "application/json", schema = Schema(implementation = FacultyDTO::class)))
                    ]
                ),
                ApiResponse(responseCode = "400"),
                ApiResponse(responseCode = "404")
            ]
        )
        @PutMapping("/api/faculties/{id}")
        @ResponseStatus(HttpStatus.ACCEPTED)
        fun update(
            @PathVariable id: Long,
            @RequestBody facultyDTO: FacultyDTO
        ): FacultyDTO = facultyService.update(id, facultyDTO)

        @Operation(summary = "Delete faculty")
        @ApiResponses(
            value = [
                ApiResponse(responseCode = "204"),
                ApiResponse(responseCode = "400"),
                ApiResponse(responseCode = "404")
            ]
        )
        @DeleteMapping("/api/faculties/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        fun delete(@PathVariable id: Long) = facultyService.delete(id)
    }
}