package com.jecminek.edventure_backend.domain.lesson

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonRepository : PagingAndSortingRepository<Lesson, Long> {

    fun findLessonByUserId(id: Long): List<Lesson>?

    fun findLessonByStartDateTimeAfterAndEndDateTimeBefore(startDateTime: Long, endDateTime: Long): List<Lesson>?

    fun findLessonByPriceBetween(price: Double, price2: Double): List<Lesson>?

    fun findLessonByOnlineIs(online: Boolean): List<Lesson>?


}