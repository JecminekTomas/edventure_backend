package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.domain.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.http.HttpStatus

import org.springframework.web.server.ResponseStatusException




@Service
class LessonService {

    @Autowired
    lateinit var repository: LessonRepository

    fun findByIdOrNull(id: Long): Lesson? = repository.findByIdOrNull(id)

    fun findLessonByUserId(id: Long): List<Lesson>? = repository.findLessonByUserId(id)

    fun delete(id: Long) {
        val lesson = repository.findByIdOrNull(id)
        if (lesson != null) {
            repository.delete(lesson)
        } else {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "Lesson not found"
            )
        }
    }

    fun update(
        id: Long,
        startDateTime: Long,
        endDateTime: Long,
        price: Double,
        online: Boolean,
        users: MutableList<User>
    ) {
        val lesson: Lesson? = findByIdOrNull(id)
        if (lesson != null) {
            lesson.startDateTime = startDateTime
            lesson.endDateTime = endDateTime
            lesson.price = price
            lesson.online = online
            lesson.users = users
            repository.save(lesson)
        } else {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "Lesson not found"
            )
        }
    }

    fun create(startDateTime: Long, endDateTime: Long, price: Double, online: Boolean, users: MutableList<User>) =
        repository.save(Lesson(startDateTime, endDateTime, price, online, users))

}