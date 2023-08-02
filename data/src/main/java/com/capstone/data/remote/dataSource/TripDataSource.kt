package com.capstone.data.remote.dataSource

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

//    @GET("/trip/member")
//    suspend fun getTripMember(
//        @Query("trup_id")
//        trip_id:Int
//    ):UserDTO
}