package com.capstone.data.remote.dataSource

import com.capstone.data.remote.dto.Trip
import com.capstone.data.remote.dto.TripDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TripDataSource {
    @GET("/trip")
    suspend fun getTrip(
        @Query("group_id")
        group_id: Int
    ): List<Trip>
}