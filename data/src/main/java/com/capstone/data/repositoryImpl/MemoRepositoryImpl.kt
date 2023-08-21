package com.capstone.data.repositoryImpl

import com.capstone.data.remote.dataSource.MemoDataSource
import com.capstone.domain.model.DomainMemo
import com.capstone.domain.repository.MemoRepository

class MemoRepositoryImpl(
    private val api: MemoDataSource
): MemoRepository {
    override suspend fun getMemo(trip_id: Int): List<DomainMemo> {
        return api.getMemo(trip_id).Data.map { it.toDomainMemo() }
    }

    override suspend fun postUrl(trip_id: Int, user_id: Int, content: String) {
        return api.postUrl(trip_id, user_id, content)
    }

}