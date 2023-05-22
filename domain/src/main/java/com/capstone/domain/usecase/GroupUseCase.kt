package com.capstone.domain.usecase

import com.capstone.domain.model.DomainGroup
import com.capstone.domain.repository.GroupRepository

class GroupUseCase(
    private val repository: GroupRepository
) {

    suspend fun getGroup(user_id: Int): List<DomainGroup>{
        return repository.getGroup(user_id)
    }
}