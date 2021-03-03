package com.jecminek.edventure_backend.domain.lesson

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface LessonRepository : PagingAndSortingRepository<Lesson, Long> {

    // TODO: ADD UPDATE


    fun findLessonByStartDateTimeAfter(startDateTime: LocalDateTime): List<Lesson>?

    fun findLessonByEndDateTimeBefore(endDateTime: LocalDateTime): List<Lesson>?

    fun findLessonByStartDateTimeAndEndDateTime(
        startDateTime: LocalDateTime, endDateTime: LocalDateTime
    ): List<Lesson>?

    fun findLessonByPriceLessThanEqual(price: Double): List<Lesson>?

    fun findLessonByPriceGreaterThanEqual(price: Double): List<Lesson>?

    fun findLessonByPriceBetween(price: Double, price2: Double): List<Lesson>?

    fun findLessonByOnlineIs(online: Boolean): List<Lesson>?

}