package com.jecminek.edventure_backend.domain.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
// TODO - Vytvořit inteface pro CRUD metody
class UserService {

    @Autowired
    lateinit var repository: UserRepository

    fun findById(id: Long): UserResponse =
        repository.findByIdOrNull(id)?.convertEntityToResponse() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun getById(id: Long): User = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

//    fun findTeachersBySubjectTitle(university: University, faculty: Faculty, title: String, page: Int): Page<User> =
//        repository.findTeachersBySubjectTitle(university, faculty, title, PageRequest.of(page, defaultPageSize), UserRole.TEACHER)

    fun create(userRequest: UserRequest): UserResponse = repository.save(userRequest.convertRequestToEntity()).convertEntityToResponse()

    fun update(id: Long, userRequest: UserRequest): UserResponse {
        val user = getById(id)
        user.firstName = userRequest.firstName
        user.lastName = userRequest.lastName
        user.email = userRequest.email
        return repository.save(userRequest.convertRequestToEntity()).convertEntityToResponse()
    }

    fun delete(id: Long) = repository.delete(getById(id))

}



