package com.jecminek.edventure_backend.domain.subject

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

// TODO: 31.03.2021 Add to Theses difference between RestCont. and Cont.
@RestController
@Tag(name = "Subjects", description = "Teachers can teach different subjects")
class SubjectController {

    @Autowired
    lateinit var subjectService: SubjectService

    // FIXME: 17.08.2021 The wrong schema.
//    @Operation(summary = "Finds all subject with requested parameters.")
//    @ApiResponses(
//        value = [
//            ApiResponse(
//                responseCode = "200", content = [
//                    (Content(mediaType = "application/json", schema = Schema(implementation = SubjectDto::class)))]
//            ),
//            ApiResponse(responseCode = "400"),
//            ApiResponse(
//                responseCode = "404",
//            )]
//    )
//    @GetMapping("/subjects")
//    fun findAll(
//        @Parameter(
//            description = "University, where faculty is being part of",
//            example = "MENDELU"
//        ) @RequestParam(required = false, defaultValue = "UNIVERSITY") university: University,
//        @Parameter(
//            description = "Faculty where subject is being taught",
//            example = "PEF"
//        )
//        @RequestParam(required = false, defaultValue = "FACULTY") faculty: Faculty,
//        @Parameter(
//            description = "Name of subject",
//            example = "Programovací techniky"
//        )
//        @RequestParam(required = false) title: String,
//        @Parameter(
//            description = "Code of subject",
//            example = "PTN"
//        )
//        @RequestParam(required = false) code: String
//    ): List<SubjectDto> = when {
//        university != University.UNIVERSITY && faculty != Faculty.FACULTY && title.isNotBlank() && code.isBlank() -> {
//            listOf(subjectService.findSubjectByTitle(university, faculty, title).convertEntityToDto())
//        }
//        university != University.UNIVERSITY && faculty == Faculty.FACULTY && title.isBlank() && code.isBlank() -> {
//            subjectService.findSubjectsByUniversity(university).map {
//                SubjectDto(
//                    id = it.id,
//                    code = it.code,
//                    title = it.title,
//                    faculty = it.faculty,
//                    university = it.university
//                )
//            }
//        }
//        university != University.UNIVERSITY && faculty != Faculty.FACULTY && title.isBlank() && code.isBlank() -> {
//            subjectService.findSubjectsByUniversityAndFaculty(university, faculty).map {
//                SubjectDto(
//                    id = it.id,
//                    code = it.code,
//                    title = it.title,
//                    faculty = it.faculty,
//                    university = it.university
//                )
//            }
//        }
//        university == University.UNIVERSITY && faculty == Faculty.FACULTY && title.isBlank() && code.isNotBlank() -> {
//            listOf(subjectService.findByCode(code).convertEntityToDto())
//        }
//        university == University.UNIVERSITY && faculty == Faculty.FACULTY && title.isBlank() && code.isBlank() -> {
//            subjectService.findAll().map {
//                SubjectDto(
//                    id = it.id,
//                    code = it.code,
//                    title = it.title,
//                    faculty = it.faculty,
//                    university = it.university
//                )
//            }
//        }
//        else -> throw ResponseStatusException(
//            HttpStatus.BAD_REQUEST,
//            "Bad request"
//        )
//    }

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

    // FIXME: 29.08.2021 RENAME ALL xxx: XxxDTO to xxx: Xxx

    @PostMapping("/subjects")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody subject: SubjectDTO): SubjectDTO =
        subjectService.create(subject)

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
        @RequestBody subject: SubjectDTO
    ): SubjectDTO = subjectService.update(id, subject)

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

