package com.jecminek.edventure_backend.domain.users

import com.jecminek.edventure_backend.domain.teachers.Teacher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
abstract class UserService {

    @Autowired
    lateinit var repository: UserRepository

    fun findByLastName(name: String): List<User> {
        return repository.findByLastName(name)
    }

    fun findAll(): MutableIterable<User> {
        return repository.findAll()
    }

    fun save(user: User): User {
        return repository.save(user)
    }

    fun delete(id: Long) {
        val user = findByIdOrNull(id)
        if (user != null) {
            repository.delete(user)
        }
    }

    fun findByIdOrNull(id: Long): User? {
        return repository.findByIdOrNull(id)
    }


}