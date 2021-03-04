package com.jecminek.edventure_backend.domain.lesson

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LessonService {

    @Autowired
    lateinit var repository: LessonRepository

    fun findByIdOrNull(id: Long): Lesson? = repository.findByIdOrNull(id)

    fun findLessonByUserId(id: Long): List<Lesson>? = repository.findLessonByUserId(id)

    fun save(lesson: Lesson): Lesson = repository.save(lesson)

    fun delete(id: Long) {
        val lesson = repository.findByIdOrNull(id)
        if (lesson != null) {
            repository.delete(lesson)
        } else {
            error("ID: $id not found.")
        }
    }

    fun create(lesson: Lesson) = repository.save(lesson)
}