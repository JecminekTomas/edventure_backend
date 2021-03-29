package com.jecminek.edventure_backend.domain.user

data class UserIdDto(
    var id: Long,
)

fun UserIdDto.convertIdDtoToEntity() = User(
    id = id
)