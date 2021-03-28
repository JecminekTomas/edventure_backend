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

    fun findById(id: Long): LessonDto = repository.findByIdOrNull(id)?.convertToDto() ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Lesson Not Found"
    )

    fun findLessonByTeachersId(teacherId: Long): MutableList<LessonDto> =
        convertLessonsMutableListToLessonsDtoMutableList(
            repository.findLessonByTeachersId(teacherId) ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Lesson Of Teacher With Id: $teacherId, Not found"
            )
        )

    fun findLessonByStudentsId(studentId: Long): MutableList<LessonDto> =
        convertLessonsMutableListToLessonsDtoMutableList(
            repository.findLessonByStudentsId(studentId) ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Lesson Of Teacher With Id: $studentId, Not found"
            )
        )


    // FIXME: 28.03.2021 This is wrong. I must find the ID.
    fun update(id: Long, lesson: LessonDto): LessonDto = repository.save(lesson.convertToEntity()).convertToDto()

    fun create(lesson: LessonDto): LessonDto = repository.save(lesson.convertToEntity()).convertToDto()

    fun delete(id: Long) = repository.delete(findById(id).convertToEntity())


}