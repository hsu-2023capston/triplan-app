package com.capstone.data.remote.dataSourceImpl

import com.capstone.data.remote.dataSource.GroupDataSource
import com.capstone.data.remote.dto.GroupDTO
import com.capstone.data.remote.dto.GroupNameDTO
import com.capstone.data.remote.dto.MessageDTO
import okhttp3.MultipartBody
import com.capstone.data.remote.dto.UserDTO
import retrofit2.Retrofit
import javax.inject.Inject

class GroupDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): GroupDataSource {
    override suspend fun getGroup(user_id: Int): GroupDTO {
        return retrofit.create(GroupDataSource::class.java).getGroup(user_id)
    }

    override suspend fun postGroup(
        group_name: String,
        group_pw: String,
        user_id: Int,
        group_path: MultipartBody.Part,
    ):MessageDTO {
        return retrofit.create(GroupDataSource::class.java).postGroup(group_name,group_pw,user_id,group_path)
    }

    override suspend fun getGroupName(group_code: String): GroupNameDTO {
        return retrofit.create(GroupDataSource::class.java).getGroupName(group_code)
    }

    override suspend fun getGroupMember(group_id: Int): UserDTO {
        return retrofit.create(GroupDataSource::class.java).getGroupMember(group_id)
    }

}