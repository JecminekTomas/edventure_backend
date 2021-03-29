package com.jecminek.edventure_backend.domain.lesson

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonRepository : PagingAndSortingRepository<Lesson, Long> {

    fun findLessonsByTeachersId(teachersId: Long): MutableList<Lesson>?

    fun findLessonsByStudentsId(studentsId: Long): MutableList<Lesson>?
}