package com.jecminek.edventure_backend.domain.lesson

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonRepository : PagingAndSortingRepository<Lesson, Long> {

    fun findLessonByTeachersId(teachersId: Long): MutableList<Lesson>?

    fun findLessonByStudentsId(studentsId: Long): MutableList<Lesson>?
}