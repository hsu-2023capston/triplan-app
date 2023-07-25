package com.capstone.data.remote.dto

import com.capstone.domain.model.DomainGroup

data class GroupDTO(
    val Message: String,
    val Data: List<Group>
)

data class Group(
    val group_id: Int,
    val group_name: String,
    val group_code: String,
    val group_pw: String,
    val group_path: String
) {
    fun toDomainGroup(): DomainGroup {
        return DomainGroup(
            group_id = group_id,
            group_name = group_name,
            group_code = group_code,
            group_pw = group_pw,
            group_path = group_path
        )
    }
}
