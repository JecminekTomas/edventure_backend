package com.jecminek.edventure_backend.domain.lesson

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonRepository : PagingAndSortingRepository<Lesson, Long> {

    fun findLessonByTeachersId(teachersId: Long, page: Pageable): Page<Lesson>

    fun findLessonByStudentsId(studentsId: Long, page: Pageable): Page<Lesson>
}