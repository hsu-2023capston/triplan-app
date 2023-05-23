package com.capstone.data.remote.dto

import com.capstone.domain.model.DomainUser
import com.capstone.domain.model.Image

data class UserDto (
        val default_id: Int?,
        val user_id: Int?,
        val user_name: String?,
        val Message: String?
        ){
        fun toDomainUser() : DomainUser {
                return DomainUser(
                        default_id = default_id,
                        user_id = user_id,
                        user_name = user_name,
                        Message = Message
                )

        }
}

