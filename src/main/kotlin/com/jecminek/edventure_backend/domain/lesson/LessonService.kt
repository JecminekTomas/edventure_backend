package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.domain.review.convertToDto
import com.jecminek.edventure_backend.domain.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException


@Service
class LessonService {

    @Autowired
    lateinit var repository: LessonRepository

    fun findById(id: Long): LessonDto = repository.findByIdOrNull(id)?.convertToDto() ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Lesson Not Found"
    )

    fun findLessonByUsersId(id: Long): List<Lesson>? =
        repository.findLessonByUsersId(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")


    fun update(
        @RequestBody lesson: LessonDto,
        @RequestParam(required = true) teachers: MutableList<User>,
        @RequestParam(required = true) students: MutableList<User>): LessonDto {
        val updatedLesson: Lesson = findById(lesson.id).convertToEntity()
        updatedLesson.startTimestamp = lesson.startTimestamp
        updatedLesson.endTimestamp = lesson.endTimestamp
        updatedLesson.price = lesson.price
        updatedLesson.online = lesson.online
        updatedLesson.teachers = teachers
        updatedLesson.students = students
        return repository.save(lesson.convertToEntity()).convertToDto()
        )).convertToDto()
    }

    fun create(lesson: LessonDto): LessonDto = repository.save(lesson.convertToEntity()).convertToDto()

    fun delete(id: Long) {
        val lesson = repository.findByIdOrNull(id)
        if (lesson != null) {
            repository.delete(lesson)
        } else {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "Lesson Not Found"
            )
        }
    }

}