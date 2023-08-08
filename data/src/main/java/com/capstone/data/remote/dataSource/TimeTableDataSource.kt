package com.capstone.data.remote.dataSource

import com.capstone.data.remote.dto.TimeTableDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeTableDataSource {
    @GET("timetable/trip")
    suspend fun getTripTimeTable(
        @Query("trip_id")
        trip_id: Int
    ): TimeTableDTO

    @GET("timetable")
    suspend fun getAllTimeTable(
        @Query("trip_id")
        trip_id: Int
    ): TimeTableDTO
}