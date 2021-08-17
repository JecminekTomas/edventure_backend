package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.constant.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class LessonService {

    @Autowired
    lateinit var repository: LessonRepository

    fun findById(id: Long): Lesson = repository.findByIdOrNull(id) ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Lesson with ID: $id, not found"
    )

    fun findLessonsByTeachersId(teacherId: Long, page: Int): Page<Lesson> =
        repository.findLessonByTeachersId(teacherId, PageRequest.of(page, Constants.defaultPageSize))

    fun findLessonsByStudentsId(studentId: Long, page: Int): Page<Lesson> =
        repository.findLessonByStudentsId(studentId, PageRequest.of(page, Constants.defaultPageSize))

    fun create(lesson: Lesson): Lesson = repository.save(lesson)

    // FIXME: 31.03.2021 Name
    fun update(id: Long, lesson: Lesson): Lesson {
        val updatedLesson = findById(id)
        updatedLesson.startTimestamp = lesson.startTimestamp
        updatedLesson.endTimestamp = lesson.endTimestamp
        updatedLesson.price = lesson.price
        updatedLesson.teachers = lesson.teachers
        updatedLesson.students = lesson.students
        return repository.save(updatedLesson)
    }

    fun delete(id: Long) = repository.delete(findById(id))

}
