package com.jecminek.edventure_backend.domain.lessons

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
class LessonController {

    @Autowired
    lateinit var lessonService: LessonService

    @GetMapping("/lessons")
    fun findAll() = lessonService.findAll()

    @GetMapping("/lessons/{id}")
    fun findByIdOrNull(@PathVariable id: Long) = lessonService.findByIdOrNull(id)

//    @GetMapping
//    fun findLessonByStartDateTimeAfter(startDateTime: LocalDateTime): List<Lesson>? = lessonService.findLessonByStartDateTimeAfter(startDateTime)
//
//    @GetMapping
//    fun findLessonByEndDateTimeBefore(endDateTime: LocalDateTime): List<Lesson>? = lessonService.findLessonByEndDateTimeBefore(endDateTime)
//
//    @GetMapping
//    fun findLessonByStartDateTimeAndEndDateTime(
//        startDateTime: LocalDateTime, endDateTime: LocalDateTime
//    ): List<Lesson>? = lessonService.findLessonByStartDateTimeAndEndDateTime(startDateTime, endDateTime)
//
//    @GetMapping
//    fun findLessonByPriceLessThanEqual(price: Double): List<Lesson>? = lessonService.findLessonByPriceLessThanEqual(price)
//
//    @GetMapping
//    fun findLessonByPriceGreaterThanEqual(price: Double): List<Lesson>? = lessonService.findLessonByPriceGreaterThanEqual(price)
//
//    @GetMapping
//    fun findLessonByPriceBetween(price: Double, price2: Double): List<Lesson>? = lessonService.findLessonByPriceBetween(price, price2)
//
//    @GetMapping
//    fun findLessonByOnlineIs(online: Boolean): List<Lesson>? = lessonService.findLessonByOnlineIs(online)

    @PostMapping("/lessons/create")
    fun create(lesson: Lesson) = lessonService.create(lesson)

    @DeleteMapping("/lessons/{id}/delete")
    fun delete(@PathVariable id: Long) = lessonService.delete(id)

    @PutMapping("/lessons/update/{id}")
    fun update(@PathVariable id: Long, lesson: Lesson) {
        lessonService.save(lesson)
    }

}