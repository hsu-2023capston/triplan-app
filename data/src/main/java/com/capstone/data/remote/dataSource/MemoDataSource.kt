package com.capstone.data.remote.dataSource

import com.capstone.data.remote.dto.MemoDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface MemoDataSource {
    @GET("class")
    suspend fun getMemo(
        @Query("trip_id")
        trip_id: Int
    ): MemoDTO

    @FormUrlEncoded
    @POST("class/url")
    suspend fun postUrl(
        @Field("trip_id") trip_id: Int,
        @Field("user_id") user_id: Int,
        @Field("content") content: String
    )
}