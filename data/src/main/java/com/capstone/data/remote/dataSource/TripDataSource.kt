package com.capstone.data.remote.dataSource

import com.capstone.data.remote.dto.TripDto
import retrofit2.http.GET

interface TripDataSource {
    @GET("/trip")
    suspend fun getTrip(): TripDto
}