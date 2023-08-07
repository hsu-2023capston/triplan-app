package com.capstone.data.remote.dto

import com.capstone.domain.model.DomainMessage

data class MessageDTO (
    val Message: String
    ){
    fun toDomainMessage(): DomainMessage{
        return DomainMessage(
            Message = Message
        )
    }
}