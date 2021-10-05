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

    fun findById(id: Long): User = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(userRequest: UserRequest): UserResponse = repository.save(userRequest.convertRequestToEntity()).convertEntityToResponse()

    fun update(id: Long, userRequest: UserRequest): UserResponse {
        val user = findById(id)
        user.firstName = userRequest.firstName
        user.lastName = userRequest.lastName
        user.email = userRequest.email
        return repository.save(user).convertEntityToResponse()
    }

    fun delete(id: Long) = repository.delete(findById(id))

}



