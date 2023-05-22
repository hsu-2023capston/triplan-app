package com.capstone.data.repositoryImpl

import com.capstone.data.remote.dataSource.GroupDataSource
import com.capstone.domain.model.DomainGroup
import com.capstone.domain.repository.GroupRepository

class GroupRepositoryImpl(
    private val api: GroupDataSource
): GroupRepository {
    override suspend fun getGroup(user_id: Int): List<DomainGroup> {
        return api.getGroup(user_id).Data.map { it.toDomainGroup() }
    }

}