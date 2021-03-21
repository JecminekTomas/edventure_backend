package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.domain.user.User
import com.jecminek.edventure_backend.domain.user.UserService
import com.jecminek.edventure_backend.domain.user.convertToEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class LessonController {

    @Autowired
    lateinit var lessonService: LessonService

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/lessons")
    fun findLessonByUsersId(@RequestParam(required = true) userId: Long): List<Lesson>? =
        lessonService.findLessonByUsersId(userId)

    @PostMapping("/lessons")
    fun create(
        @RequestParam(required = true) teacherId: Long,
        @RequestBody lesson: LessonDto
    ){
        val teacher = userService.findById(teacherId)
        val newLesson = lesson.convertToEntity()

        newLesson.teachers.add(teacher.convertToEntity())
        lessonService.create(newLesson.convertToDto())
    }

    /** fun create(@RequestBody lesson: Lesson)*/

    @DeleteMapping("/lessons/{id}")
    fun delete(@PathVariable id: Long) = lessonService.delete(id)

    @PutMapping("/lessons/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestParam(required = true) startDateTime: Long,
        @RequestParam(required = true) endDateTime: Long,
        @RequestParam(required = true) price: Double,
        @RequestParam(required = true) online: Boolean,
        @RequestParam(required = true) users: MutableList<User>
    ) = lessonService.update(startDateTime, endDateTime, endDateTime, price, online, users)
}
