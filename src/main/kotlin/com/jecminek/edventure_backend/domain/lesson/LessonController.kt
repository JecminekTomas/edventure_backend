package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.domain.user.User
import com.jecminek.edventure_backend.domain.user.UserService
import com.jecminek.edventure_backend.enums.UserRole
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

    @GetMapping("/lessons/teachers/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findLessonsByTeachersId(@PathVariable id: Long): List<LessonDto> =
        lessonService.findLessonsByTeachersId(id).map {
            it.convertEntityToDto()
        }

    @GetMapping("/lessons/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findLessonsByStudentsId(@PathVariable id: Long): List<LessonDto> =
        lessonService.findLessonsByStudentsId(id).map {
            it.convertEntityToDto()
        }


    @PostMapping("/lessons")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody lesson: LessonDto
    ): LessonDto {
        lesson.teachers.forEach { newTeacher ->
            val teacher = userService.findById(newTeacher.id)
            if (!teacher.roles.contains(UserRole.TEACHER)) {
                throw ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Not found teacher with ID: ${teacher.id}"
                )
            }
        }
        lesson.students.forEach { newStudent ->
            val student = userService.findById(newStudent.id)
            if (!student.roles.contains(UserRole.STUDENT)) {
                throw ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Not found student with ID: ${student.id}"
                )
            }
        }
        return lessonService.create(lesson.convertDtoToEntity()).convertEntityToDto()
    }


    // FIXME: 28.03.2021 This is wrong. I must find the ID.
    // FIXME: 28.03.2021 WARNING: WHAT THE HELL, IT IS COMPLETELY WRONG

    @PutMapping("/lessons/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @PathVariable id: Long,
        @RequestBody lesson: LessonDto
    ): LessonDto {
        lesson.teachers.forEach { newTeacher ->
            val teacher = userService.findById(newTeacher.id)
            if (!teacher.roles.contains(UserRole.TEACHER)) {
                throw ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Not found teacher with ID: ${teacher.id}"
                )
            }
        }
        lesson.students.forEach { newStudent ->
            val student = userService.findById(newStudent.id)
            if (!student.roles.contains(UserRole.STUDENT)) {
                throw ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Not found student with ID: ${student.id}"
                )
            }
        }
        return lessonService.update(id, lesson.convertDtoToEntity()).convertEntityToDto()
    }

    @DeleteMapping("/lessons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = lessonService.delete(id)


    fun LessonDto.convertDtoToEntity() = Lesson(
        startTimestamp = startTimestamp,
        endTimestamp = endTimestamp,
        price = price,
        online = online,
        teachers = teachers.map {
            userService.findById(it.id)
        } as MutableList<User>,
        students = students.map {
            userService.findById(it.id)
        } as MutableList<User>
    )


}
