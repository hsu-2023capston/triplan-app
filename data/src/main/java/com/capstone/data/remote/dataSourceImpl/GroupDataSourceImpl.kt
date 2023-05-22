package com.capstone.data.remote.dataSourceImpl

import com.capstone.data.remote.dataSource.GroupDataSource
import com.capstone.data.remote.dto.GroupDto
import retrofit2.Retrofit
import javax.inject.Inject

class GroupDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): GroupDataSource {
    override suspend fun getGroup(user_id: Int): GroupDto {
        return retrofit.create(GroupDataSource::class.java).getGroup(user_id)
    }
}