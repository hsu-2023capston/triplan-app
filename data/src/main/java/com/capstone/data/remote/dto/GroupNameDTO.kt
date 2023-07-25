package com.capstone.data.remote.dto

import com.capstone.domain.model.DomainGroupName

data class GroupNameDTO(
    val Message: String,
    val Data: String
) {
    fun toDomainGroupName(): DomainGroupName {
        return DomainGroupName(
            Data = Data,
            Message = Message
        )
    }
}
