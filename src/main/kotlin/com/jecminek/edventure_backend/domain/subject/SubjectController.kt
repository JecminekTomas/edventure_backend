package com.jecminek.edventure_backend.domain.subject

import com.jecminek.edventure_backend.domain.university.UniversityDTO
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
@Tag(name = "Subject", description = "Users can teach different subjects")
class SubjectController {

    @Autowired
    lateinit var subjectService: SubjectService

    @Operation(summary = "Finds all subjects with requested parameters.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = SubjectDTO::class)))]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(
                responseCode = "404",
            )]
    )
    @GetMapping("/subjects")
    fun findAll(
        @Parameter(
            description = "Id of faculty where subject is being taught",
            example = "1"
        )
        @RequestParam(required = false) facultyId: Long?
    ): List<SubjectDTO> = subjectService.findAll(facultyId)

    @GetMapping("/subjects/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(
        @Parameter(
            description = "Id of subject to be found",
            example = "1"
        ) @PathVariable id: Long
    ): SubjectDTO =
        subjectService.getById(id)


    @Operation(summary = "Create subject")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = SubjectDTO::class)))
                ]
            ),
            ApiResponse(responseCode = "400")
        ]
    )

    @PostMapping("/subjects")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody subjectDTO: SubjectDTO): SubjectDTO =
        subjectService.create(subjectDTO)

    @Operation(summary = "Update subject")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "202",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = SubjectDTO::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @PutMapping("/subjects/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @PathVariable id: Long,
        @RequestBody subjectDTO: SubjectDTO
    ): SubjectDTO = subjectService.update(id, subjectDTO)

    @Operation(summary = "Delete subject")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204"),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @DeleteMapping("/subjects/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = subjectService.delete(id)

}

