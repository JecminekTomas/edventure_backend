package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.domain.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class LessonController {

    @Autowired
    lateinit var service: LessonService

    @GetMapping("/lessons")
    fun findLessonByUserId(@RequestParam(required = true) userId: Long): List<Lesson>? =
        service.findLessonByUserId(userId)

    @PostMapping("/lessons")
    fun create(
        id: Long,
        @RequestParam(required = true) startDateTime: Long,
        @RequestParam(required = true) endDateTime: Long,
        @RequestParam(required = true) price: Double,
        @RequestParam(required = true) online: Boolean,
        @RequestParam(required = true) users: MutableList<User>
    ) = service.create(startDateTime, endDateTime, price, online, users)

    @DeleteMapping("/lessons/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)

    @PutMapping("/lessons/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestParam(required = true) startDateTime: Long,
        @RequestParam(required = true) endDateTime: Long,
        @RequestParam(required = true) price: Double,
        @RequestParam(required = true) online: Boolean,
        @RequestParam(required = true) users: MutableList<User>
    ) = service.update(startDateTime, endDateTime, endDateTime, price, online, users)
}
