package com.capstone.data.remote.dataSource

import com.capstone.data.remote.dto.GroupDTO

import com.capstone.data.remote.dto.GroupNameDTO
import com.capstone.data.remote.dto.MessageDTO
import okhttp3.MultipartBody
import retrofit2.http.*
import com.capstone.data.remote.dto.UserDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GroupDataSource {
    @GET("group")
    suspend fun getGroup(
        @Query("user_id")
        user_id: Int
    ) : GroupDTO

    @Multipart
    @POST("group/new")
    suspend fun postGroup(
        @Part("group_name") group_name: String,
        @Part("group_pw") group_pw: String,
        @Part("user_id") user_id: Int,
        @Part group_path: MultipartBody.Part
    ): MessageDTO

    @GET("group/join")
    suspend fun getGroupName(
        @Query("group_code") group_code: String
    ): GroupNameDTO

    @GET("/group/member")
    suspend fun getGroupMember(
        @Query("group_id")
        group_id: Int
    ): UserDTO
}