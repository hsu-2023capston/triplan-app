package com.capstone.data.remote.dataSource

import com.capstone.data.remote.dto.UserDto
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserDataSource {
    @GET("/login")
    suspend fun getUserLogin(
        @Query("access_token")
        access_token: String
    ) : UserDto

    @FormUrlEncoded
    @POST("/login")
    suspend fun postUserLogin(
        @Field("user_name") user_name: String,
        @Field("access_token") access_token: String,
        @Field("default_id") default_id:Int
    ) :UserDto
}