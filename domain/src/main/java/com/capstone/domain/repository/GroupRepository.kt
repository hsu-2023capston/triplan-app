package com.capstone.domain.repository

import com.capstone.domain.model.DomainGroup
import com.capstone.domain.model.DomainUser

interface GroupRepository {
    suspend fun getGroup(user_id: Int): List<DomainGroup>

    suspend fun getGroupMember(group_id: Int):List<DomainUser>
}