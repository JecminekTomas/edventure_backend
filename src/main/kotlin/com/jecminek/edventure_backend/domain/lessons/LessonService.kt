package com.jecminek.edventure_backend.domain.lessons

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LessonService {
    //TODO: ADD UPDATE

    @Autowired
    lateinit var lessonRepository: LessonRepository

    fun findAll(): MutableIterable<Lesson> = lessonRepository.findAll()

    fun findByIdOrNull(id: Long): Lesson? = lessonRepository.findByIdOrNull(id)

    fun findLessonByStartDateTimeAfter(startDateTime: LocalDateTime): List<Lesson>? =
        lessonRepository.findLessonByStartDateTimeAfter(startDateTime)

    fun findLessonByEndDateTimeBefore(endDateTime: LocalDateTime): List<Lesson>? =
        lessonRepository.findLessonByEndDateTimeBefore(endDateTime)

    fun findLessonByStartDateTimeAndEndDateTime(
        startDateTime: LocalDateTime, endDateTime: LocalDateTime
    ): List<Lesson>? = lessonRepository.findLessonByStartDateTimeAndEndDateTime(startDateTime, endDateTime)

    fun findLessonByPriceLessThanEqual(price: Double): List<Lesson>? =
        lessonRepository.findLessonByPriceLessThanEqual(price)

    fun findLessonByPriceGreaterThanEqual(price: Double): List<Lesson>? =
        lessonRepository.findLessonByPriceGreaterThanEqual(price)

    fun findLessonByPriceBetween(price: Double, price2: Double): List<Lesson>? =
        lessonRepository.findLessonByPriceBetween(price, price2)

    fun findLessonByOnlineIs(online: Boolean): List<Lesson>? =
        lessonRepository.findLessonByOnlineIs(online)

    fun save(lesson: Lesson): Lesson = lessonRepository.save(lesson)

    fun delete(id: Long) {
        val lesson = lessonRepository.findByIdOrNull(id)
        if (lesson != null) {
            lessonRepository.delete(lesson)
        } else{
            error("ID: $id not found.")
        }
    }

    fun create(lesson: Lesson) = lessonRepository.save(lesson)
}