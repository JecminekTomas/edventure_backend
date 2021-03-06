package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.domain.lesson.Lesson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
// TODO - Vytvořit inteface pro CRUD metody
class UserService {
    // TODO: ROLE, STATUS

    @Autowired
    lateinit var repository: UserRepository

    fun findByIdOrNull(id: Long): User? = repository.findByIdOrNull(id)

    fun findUserByRole(role: UserRole): List<User>? = repository.findUserByRole(role)

    fun create(
        firstName: String,
        lastName: String,
        email: String,
        biography: String,
        phoneNumber: String,
        roles: MutableList<UserRole>,
    ): User = repository.save(User(firstName, lastName, email, biography, phoneNumber, UserStatus.ONLINE, roles))


    fun delete(id: Long) {
        val teacher = findByIdOrNull(id)
        if (teacher != null) {
            repository.delete(teacher)
        } else {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found."
            )
        }
    }

    fun update(
        id: Long,
        firstName: String,
        lastName: String,
        email: String,
        biography: String,
        phoneNumber: String,
        status: UserStatus,
        roles: MutableList<UserRole>,
        lessons: MutableList<Lesson>
    ) {
        // FIXME - Vytvořit DTO!.
        val user = findByIdOrNull(id)
        if(user != null) {
            user.firstName = firstName
            user.lastName = lastName
            user.email = email
            user.biography = biography
            user.phoneNumber = phoneNumber
            user.status = status
            user.roles = roles
            user.lessons = lessons
        }
    } // FIXME - Návratový typ
}