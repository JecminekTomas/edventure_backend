package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.domain.review.ReviewDto
import com.jecminek.edventure_backend.domain.user.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class LessonController {

    @Autowired
    lateinit var lessonService: LessonService

    @Autowired
    lateinit var userService: UserService


    @GetMapping("/lessons/teachers/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findLessonByTeachersId(@PathVariable id: Long): List<LessonDto> =
        lessonService.findLessonByTeachersId(id)

    @GetMapping("/lessons/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findLessonByStudentsId(@PathVariable id: Long): List<LessonDto> =
        lessonService.findLessonByStudentsId(id)


    @PostMapping("/lessons")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody lesson: LessonDto
    )  {
        // TODO: 27.03.2021 Throws exception and stops, or save the rest?
        lesson.teachers.forEach { teacher ->
            userService.findById(teacher.id)
        }
        lesson.students.forEach { student ->
            userService.findById(student.id)
        }
        lessonService.create(lesson)
    }


    /** fun create(@RequestBody lesson: Lesson)*/

    @DeleteMapping("/lessons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = lessonService.delete(id)

    @PutMapping("/lessons/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @PathVariable id: Long,
        @RequestBody lesson: LessonDto
    ) = lessonService.update(id, lesson)
}
