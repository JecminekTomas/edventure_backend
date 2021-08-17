package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.constant.Constants.defaultPageSize
import com.jecminek.edventure_backend.enums.Faculty
import com.jecminek.edventure_backend.enums.University
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

    fun findById(id: Long): UserDto =
        repository.findByIdOrNull(id)?.convertEntityToDto() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

//    fun findTeachersBySubjectTitle(university: University, faculty: Faculty, title: String, page: Int): Page<User> =
//        repository.findTeachersBySubjectTitle(university, faculty, title, PageRequest.of(page, defaultPageSize), UserRole.TEACHER)

    fun create(userDto: UserDto): UserDto = repository.save(userDto.convertDtoToEntity()).convertEntityToDto()

    fun update(id: Long, userDto: UserDto): UserDto {
        val updatedUser = findById(id)
        updatedUser.firstName = userDto.firstName
        updatedUser.lastName = userDto.lastName
        updatedUser.email = userDto.email
        updatedUser.biography = userDto.biography
        updatedUser.phoneNumber = userDto.phoneNumber
        updatedUser.roles = userDto.roles
        return repository.save(userDto.convertDtoToEntity()).convertEntityToDto()
    }

    fun delete(id: Long) = repository.delete(findById(id).convertDtoToEntity())

}

fun User.convertEntityToDto(): UserDto = UserDto(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    biography = biography,
    phoneNumber = phoneNumber,
    roles = roles
)

fun UserDto.convertDtoToEntity() = User(
    firstName = firstName,
    lastName = lastName,
    email = email,
    biography = biography,
    phoneNumber = phoneNumber,
    roles = roles
)


