package com.capstone.data.remote.dataSourceImpl

import com.capstone.data.remote.dataSource.GroupDataSource
import com.capstone.data.remote.dto.GroupDto
import com.capstone.data.remote.dto.UserDto
import retrofit2.Retrofit
import javax.inject.Inject

class GroupDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): GroupDataSource {
    override suspend fun getGroup(user_id: Int): List<GroupDto> {
        return retrofit.create(GroupDataSource::class.java).getGroup(user_id)
    }

    override suspend fun getGroupMember(group_id: Int): List<UserDto> {
        return retrofit.create(GroupDataSource::class.java).getGroupMember(group_id)
    }
}