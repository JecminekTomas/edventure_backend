package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.enums.UserRole
import com.jecminek.edventure_backend.extensions.EntityMutableListToDtoMutableListConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
// TODO - Vytvořit inteface pro CRUD metody
class UserService : EntityMutableListToDtoMutableListConverter<User, UserDto> {
    // TODO: STATUS

    @Autowired
    lateinit var repository: UserRepository

    fun findById(id: Long): UserDto =
        repository.findByIdOrNull(id)?.convertEntityToDto() ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User With Id: $id, Not Found"
        )

    // FIXME: 28.03.2021 Repo MUST return Entity, but Dto MUST be in respose.
    fun findUserByRole(role: UserRole): MutableList<UserDto> =
        convertMutableLists(
            repository.findUserByRoles(role) ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User With Role: $role, Not Found"
            )
        )

    fun create(userDto: UserDto): UserDto = repository.save(userDto.convertToEntity()).convertEntityToDto()

    fun update(id: Long, userDto: UserDto): UserDto {
        val user = findById(id)
        user.id = id
        user.firstName = userDto.firstName
        user.lastName = userDto.lastName
        user.email = userDto.email
        user.biography = userDto.biography
        user.phoneNumber = userDto.phoneNumber
        user.roles = userDto.roles
        return repository.save(user.convertToEntity()).convertEntityToDto()
    }

    fun delete(id: Long) = repository.delete(findById(id).convertToEntity())

    override fun convertMutableLists(list: MutableList<User>): MutableList<UserDto> {
        val usersDto = mutableListOf<UserDto>()
        for (user in list) {
            usersDto.add(user.convertEntityToDto())
        }
        return usersDto
    }
}