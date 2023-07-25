package com.capstone.data.remote.dataSource

import com.capstone.data.remote.dto.GroupDto
import com.capstone.data.remote.dto.GroupNameDto
import okhttp3.MultipartBody
import retrofit2.http.*

interface GroupDataSource {
    @GET("group")
    suspend fun getGroup(
        @Query("user_id")
        user_id: Int
    ) : List<GroupDto>

    @Multipart
    @POST("group/new")
    suspend fun postGroup(
        @Part("group_name") group_name: String,
        @Part("group_pw") group_pw: String,
        @Part("user_id") user_id: Int,
        @Part group_path: MultipartBody.Part
    )

    @GET("group/join")
    suspend fun getGroupName(
        @Query("group_code") group_code: String
    ): GroupNameDto
}