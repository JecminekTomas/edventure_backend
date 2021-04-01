package com.jecminek.edventure_backend.domain.subject

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

// TODO: 31.03.2021 Add to Theses difference between RestCont. and Cont.
@RestController
class SubjectController {

    @Autowired
    lateinit var subjectService: SubjectService

    @Operation(summary = "Find all subjects")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Subjects found", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = SubjectDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(
                responseCode = "404",
                description = "Subjects not found",
                content = [Content()]
            )]
    )
    @GetMapping("/subjects")
    fun findAll(): List<SubjectDto> = subjectService.findAll().map {
        SubjectDto(id = it.id, code = it.code, title = it.title, faculty = it.faculty)
    }

    @Operation(summary = "Creation of subject")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201", description = "Subject created", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = SubjectDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()])]
    )
    @PostMapping("/subjects")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody subject: SubjectDto): SubjectDto =
        subjectService.create(subject.convertDtoToEntity()).convertEntityToDto()

    @Operation(summary = "Update subject")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "202", description = "Subject updated", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = SubjectDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Subject with this ID not found", content = [Content()])]
    )
    @PutMapping("/subjects/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @PathVariable id: Long,
        @RequestBody subject: SubjectDto
    ): SubjectDto = subjectService.update(id, subject.convertDtoToEntity()).convertEntityToDto()

    @Operation(summary = "Delete subject")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Subject deleted", content = [Content()]),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Subject with this ID not found", content = [Content()])]
    )
    @DeleteMapping("/subjects/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = subjectService.delete(id)

    fun Subject.convertEntityToDto() = SubjectDto(
        id = id,
        code = code,
        title = title,
        faculty = faculty
    )

    fun SubjectDto.convertDtoToEntity() = Subject(
        code = code,
        title = title,
        faculty = faculty
    )

}

