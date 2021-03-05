package com.jecminek.edventure_backend.domain.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    lateinit var repository: UserRepository

    fun findByIdOrNull(id: Long): User? = repository.findByIdOrNull(id)

    fun findUserByRole(role: UserRole): List<User>? = repository.findUserByRole(role)

    fun save(firstName: String, lastName: String, email: String, biography: String?, phoneNumber: String?): User {
        val user = User(firstName, lastName, email)
        if (biography != null) {
            user.biography = biography
        }
        if (phoneNumber != null) {
            user.biography = biography
        }
        return repository.save(user)
    }

    fun delete(id: Long) {
        val teacher = findByIdOrNull(id)
        if (teacher != null) {
            repository.delete(teacher)
        }
    }

    fun update(
        id: Long,
        firstName: String?,
        lastName: String?,
        email: String?,
        biography: String?,
        phoneNumber: String?
    ) {
        if (firstName != null || lastName != null || email != null || biography != null || phoneNumber != null) {
            val original = findByIdOrNull(id)
            if (original != null) {
                repository.delete(original) // FIXME OR NOT?
                if (firstName != null) {
                    original.firstName = firstName
                }
                if (lastName != null) {
                    original.lastName = lastName
                }
                if (email != null) {
                    original.email = email
                }
                if (biography != null) {
                    original.biography = biography
                }
                if (phoneNumber != null) {
                    original.phoneNumber = phoneNumber
                }
                original.status = UserStatus.ONLINE
                repository.save(original)
            }
        }
    }
}