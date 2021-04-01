package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.constant.Constants.defaultPageSize
import com.jecminek.edventure_backend.enums.UserRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
// TODO - Vytvořit inteface pro CRUD metody
class UserService {

    @Autowired
    lateinit var repository: UserRepository

    fun findById(id: Long): User =
        repository.findByIdOrNull(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User With Id: $id, Not Found"
        )

    fun findUserByRole(role: UserRole, page: Int): Page<User> =
        repository.findUserByRoles(role, PageRequest.of(page, defaultPageSize)) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User With Role: $role, Not Found"
        )

    fun create(userDto: UserDto): User = repository.save(userDto.convertDtoToEntity())

    fun update(id: Long, userDto: UserDto): User {
        val updatedUser = findById(id)
        updatedUser.firstName = userDto.firstName
        updatedUser.lastName = userDto.lastName
        updatedUser.email = userDto.email
        updatedUser.biography = userDto.biography
        updatedUser.phoneNumber = userDto.phoneNumber
        updatedUser.roles = userDto.roles
        return repository.save(updatedUser)
    }

    fun delete(id: Long) = repository.delete(findById(id))
}

