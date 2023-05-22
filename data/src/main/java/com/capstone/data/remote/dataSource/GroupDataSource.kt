package com.capstone.data.remote.dataSource

import com.capstone.data.remote.dto.GroupDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GroupDataSource {
    @GET("group")
    suspend fun getGroup(
        @Query("user_id")
        user_id: Int
    ) : GroupDto
}