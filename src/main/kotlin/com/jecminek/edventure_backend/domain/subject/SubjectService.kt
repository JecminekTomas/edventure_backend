package com.jecminek.edventure_backend.domain.subject

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SubjectService {

    @Autowired
    lateinit var repository: SubjectRepository

    fun findAll(): MutableIterable<Subject> = repository.findAll()

    fun findById(id: Long): Subject =
        repository.findByIdOrNull(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Subject With Id: $id, Not Found"
        )

    fun create(subject: Subject): Subject = repository.save(subject)

    fun update(id: Long, subject: Subject): Subject {
        val updatedSubject = findById(id)
        updatedSubject.code = subject.code
        updatedSubject.title = subject.title
        updatedSubject.lessons = subject.lessons
        return repository.save(updatedSubject)
    }

    fun delete(id: Long) = repository.delete(findById(id))

}