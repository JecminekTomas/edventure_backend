package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.domain.user.UserDto

data class LessonDto(
    var id: Long,
    var startTimestamp: Long,
    var endTimestamp: Long,
    var price: Double,
    var online: Boolean,
    //var teachers: MutableList<UserDto>,
    //var students: MutableList<UserDto>
)

fun LessonDto.convertToEntity() = Lesson(
    startTimestamp = startTimestamp,
    endTimestamp = endTimestamp,
    price = price,
    online = online
)