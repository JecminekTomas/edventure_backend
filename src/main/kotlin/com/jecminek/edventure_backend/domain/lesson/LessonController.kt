package com.jecminek.edventure_backend.domain.lesson

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RequestMapping("/lessons")
@RestController
class LessonController {

    @Autowired
    lateinit var service: LessonService

    @GetMapping("/")
    fun findLessonByUserId(@RequestParam(required = true) userId: Long): List<Lesson>? =
        service.findLessonByUserId(userId)

    @PostMapping("/create")
    fun create(lesson: Lesson) = service.create(lesson)

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)

    @PutMapping("/update/{id}")
    fun update(@PathVariable id: Long) {
        val lesson = service.findByIdOrNull(id)
        if (lesson != null) {
            service.save(lesson)
        } else {
            error("ID: $id is not exist.")
        }
    }
}
