package com.capstone.data.remote.dataSource

import com.capstone.data.remote.dto.GroupDto
import com.capstone.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GroupDataSource {
    @GET("group")
    suspend fun getGroup(
        @Query("user_id")
        user_id: Int
    ) : List<GroupDto>

    @GET("/group/member")
    suspend fun getGroupMember(
        @Query("group_id")
        group_id: Int
    ): List<UserDto>
}