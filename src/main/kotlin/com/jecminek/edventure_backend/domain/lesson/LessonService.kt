package com.jecminek.edventure_backend.domain.lesson

import org.springframework.beans.factory.annotation.Autowired
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

    fun findLessonsByTeachersId(teacherId: Long): MutableList<Lesson> =
        repository.findLessonByTeachersId(teacherId) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Lesson of teacher with id: $teacherId, not found"
        )

    fun findLessonsByStudentsId(studentId: Long): MutableList<Lesson> =
        repository.findLessonByStudentsId(studentId) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Lesson of teacher with id: $studentId, not found"
        )

    fun create(lesson: Lesson): Lesson = repository.save(lesson)

    fun update(id: Long, updatedLesson: Lesson): Lesson {
        val lesson= findById(id)
        lesson.startTimestamp = updatedLesson.startTimestamp
        lesson.endTimestamp = updatedLesson.endTimestamp
        lesson.price = updatedLesson.price
        lesson.online = updatedLesson.online
        lesson.teachers = updatedLesson.teachers
        lesson.students = updatedLesson.students
        return repository.save(lesson)
    }

    fun delete(id: Long) = repository.delete(findById(id))

}
