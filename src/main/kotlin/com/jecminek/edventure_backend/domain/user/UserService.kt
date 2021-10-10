package com.jecminek.edventure_backend.domain.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
// TODO - Vytvořit inteface pro CRUD metody
class UserService {

    @Autowired
    lateinit var repository: UserRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    fun findById(id: Long): User = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(userRequest: UserRequest): UserResponse {
        val user = User()
        user.email = userRequest.email
        user.firstName = userRequest.firstName
        user.lastName = userRequest.lastName
        user.password = passwordEncoder.encode(userRequest.password)
        return repository.save(user).convertEntityToResponse()
    }

    fun update(id: Long, userRequest: UserRequest): UserResponse {
        val user = findById(id)
        user.firstName = userRequest.firstName
        user.lastName = userRequest.lastName
        user.email = userRequest.email
        user.password = passwordEncoder.encode(userRequest.password)
        return repository.save(user).convertEntityToResponse()
    }

    fun delete(id: Long) = repository.delete(findById(id))

}



