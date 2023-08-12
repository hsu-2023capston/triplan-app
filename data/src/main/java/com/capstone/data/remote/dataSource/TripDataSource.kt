package com.capstone.data.remote.dataSource

import com.capstone.data.remote.dto.ListTripDTO
import com.capstone.data.remote.dto.TripDTO
import com.capstone.data.remote.dto.UserDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface TripDataSource {
    @GET("/trip")
    suspend fun getTrip(
        @Query("group_id")
        group_id: Int
    ): TripDTO

    @GET("/trip/member")
    suspend fun getTripMember(
        @Query("trip_id")
        trip_id:Int
    ):UserDTO

    @GET("/trip/home")
    suspend fun getTripHome(
        @Query("user_id")
        user_id: Int
    ): ListTripDTO
}