package com.capstone.data.remote.dto

import com.capstone.domain.model.DomainGroupName

data class GroupNameDto(
    val Data: String,
    val Message: String
){
    fun toDomainGroupName() : DomainGroupName{
        return DomainGroupName(
            Data = Data,
            Message = Message
        )
    }
}
