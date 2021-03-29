package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.domain.user.User
import com.jecminek.edventure_backend.domain.user.UserService
import com.jecminek.edventure_backend.enums.UserRole
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class LessonController {

    @Autowired
    lateinit var lessonService: LessonService

    @Autowired
    lateinit var userService: UserService

    @Operation(summary = "Find lessons by ID of user, who is in position of teacher at lesson")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Lesson(s) found", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = LessonDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(
                responseCode = "404",
                description = "Lesson(s) with this ID of user in position of teacher not found",
                content = [Content()]
            )]
    )
    @GetMapping("/lessons/teachers/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findLessonsByTeachersId(@PathVariable id: Long): List<LessonDto> =
        lessonService.findLessonsByTeachersId(id).map {
            it.convertEntityToDto()
        }

    @Operation(summary = "Find lessons by ID of user, who is in position of student at lesson")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Lesson(s) found", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = LessonDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(
                responseCode = "404",
                description = "Lesson(s) with this ID of user in position of student not found",
                content = [Content()]
            )]
    )
    @GetMapping("/lessons/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findLessonsByStudentsId(@PathVariable id: Long): List<LessonDto> =
        lessonService.findLessonsByStudentsId(id).map {
            it.convertEntityToDto()
        }


    @Operation(summary = "Creation of lesson")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201", description = "Lesson created", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = LessonDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()])]
    )
    @PostMapping("/lessons")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody lesson: LessonDto
    ): LessonDto {
        lesson.teachersIds.forEach { teacherId ->
            val teacher = userService.findById(teacherId)
            if (!teacher.roles.contains(UserRole.TEACHER)) {
                throw ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Not found teacher with ID: ${teacher.id}"
                )
            }
        }
        lesson.studentsIds.forEach { studentId ->
            val student = userService.findById(studentId)
            if (!student.roles.contains(UserRole.STUDENT)) {
                throw ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Not found student with ID: ${student.id}"
                )
            }
        }
        return lessonService.create(lesson.convertDtoToEntity()).convertEntityToDto()
    }

    @Operation(summary = "Update lesson")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "202", description = "Lesson updated", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = LessonDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Lesson with this ID not found", content = [Content()])]
    )
    @PutMapping("/lessons/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @PathVariable id: Long,
        @RequestBody lesson: LessonDto
    ): LessonDto {
        lesson.teachersIds.forEach { teacherId ->
            val teacher = userService.findById(teacherId)
            if (!teacher.roles.contains(UserRole.TEACHER)) {
                throw ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Not found teacher with ID: ${teacher.id}"
                )
            }
        }
        lesson.studentsIds.forEach { studentId->
            val student = userService.findById(studentId)
            if (!student.roles.contains(UserRole.STUDENT)) {
                throw ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Not found student with ID: ${student.id}"
                )
            }
        }
        return lessonService.update(id, lesson.convertDtoToEntity()).convertEntityToDto()
    }


    @Operation(summary = "Delete lesson")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Lesson deleted", content = [Content()]),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Lesson with this ID not found", content = [Content()])]
    )
    @DeleteMapping("/lessons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = lessonService.delete(id)


    fun LessonDto.convertDtoToEntity() = Lesson(
        startTimestamp = startTimestamp,
        endTimestamp = endTimestamp,
        price = price,
        online = online,
        teachers = teachersIds.map {
            userService.findById(it)
        } as MutableList<User>,
        students = studentsIds.map {
            userService.findById(it)
        } as MutableList<User>
    )

    fun Lesson.convertEntityToDto() = LessonDto(
        id = id,
        startTimestamp = endTimestamp,
        endTimestamp = endTimestamp,
        price = price,
        online = online,
        teachersIds = teachers.map {
            it.id
        } as MutableList<Long>,
        studentsIds = students.map {
            it.id
        } as MutableList<Long>
    )


}
