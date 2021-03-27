package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.domain.user.User
import com.jecminek.edventure_backend.domain.user.UserDto
import com.jecminek.edventure_backend.domain.user.convertToEntity

data class LessonDto(
    var id: Long,
    var startTimestamp: Long,
    var endTimestamp: Long,
    var price: Double,
    var online: Boolean,
    var teachers: MutableList<UserDto>,
    var students: MutableList<UserDto>
)

// TODO: 27.03.2021 Create Dto, with sending ID's only

fun LessonDto.convertToEntity() = Lesson(
    startTimestamp = startTimestamp,
    endTimestamp = endTimestamp,
    price = price,
    online = online,
    teachers = convertUsersDtoMutableListToUsersMutableList(teachers),
    students = convertUsersDtoMutableListToUsersMutableList(students)
)

fun convertUsersDtoMutableListToUsersMutableList(usersDto: MutableList<UserDto>): MutableList<User>{
    val users: MutableList<User> = mutableListOf()
    for (userDto in usersDto) {
        users.add(userDto.convertToEntity())
    }
    return users
}