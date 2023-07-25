package com.capstone.data.remote.dto

import com.capstone.domain.model.DomainUser

data class UserDTO(
    val Message: String,
    val Data: List<User>
)

data class User(
    val default_id: Int?,
    val user_id: Int?,
    val user_name: String?,
    val Message: String?,
    val trip_cnt: Int?,
) {
    fun toDomainUser(): DomainUser {
        return DomainUser(
            default_id = default_id,
            user_id = user_id,
            user_name = user_name,
            Message = Message,
            trip_cnt = trip_cnt
        )

    }
}

