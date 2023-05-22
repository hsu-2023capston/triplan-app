package com.capstone.domain.repository

import com.capstone.domain.model.DomainGroup

interface GroupRepository {
    suspend fun getGroup(user_id: Int): List<DomainGroup>
}