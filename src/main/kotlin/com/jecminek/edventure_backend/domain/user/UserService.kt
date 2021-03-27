package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.enums.UserRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
// TODO - Vytvořit inteface pro CRUD metody
class UserService {
    // TODO: STATUS

    @Autowired
    lateinit var repository: UserRepository

    fun findById(id: Long): UserDto =
        repository.findByIdOrNull(id)?.convertToDto() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User With Id: $id, Not Found")

    fun findUserByRole(role: UserRole): List<User> =
        repository.findUserByRoles(role) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User With Role: $role, Not Found")

    fun create(userDto: UserDto): UserDto = repository.save(userDto.convertToEntity()).convertToDto()

    fun update(id: Long, userDto: UserDto): UserDto {
        val user = findById(id)
        user.firstName = userDto.firstName
        user.lastName = userDto.lastName
        user.email = userDto.email
        user.biography = userDto.biography
        user.phoneNumber = userDto.phoneNumber
        user.roles = userDto.roles
        return repository.save(user.convertToEntity()).convertToDto()
    }

    fun delete(id: Long) = repository.delete(findById(id).convertToEntity())


}