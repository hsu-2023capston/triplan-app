package com.capstone.data.remote.dataSourceImpl

import com.capstone.data.remote.dataSource.GroupDataSource
import com.capstone.data.remote.dto.GroupDto
import okhttp3.MultipartBody
import retrofit2.Retrofit
import javax.inject.Inject

class GroupDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): GroupDataSource {
    override suspend fun getGroup(user_id: Int): List<GroupDto> {
        return retrofit.create(GroupDataSource::class.java).getGroup(user_id)
    }

    override suspend fun postGroup(
        group_name: String,
        group_pw: String,
        user_id: Int,
        group_path: MultipartBody.Part,
    ) {
        retrofit.create(GroupDataSource::class.java).postGroup(group_name,group_pw,user_id,group_path)
    }

}