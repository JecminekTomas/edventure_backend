package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.domain.user.UserDto
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonRepository : PagingAndSortingRepository<Lesson, Long> {

    fun findLessonByTeachersId(teachersId: Long): MutableList<Lesson>?

    fun findLessonByStudentsId(studentsId: Long): MutableList<Lesson>?
}