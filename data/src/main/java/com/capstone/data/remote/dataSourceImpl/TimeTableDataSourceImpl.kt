package com.capstone.data.remote.dataSourceImpl

import com.capstone.data.remote.dataSource.TimeTableDataSource
import com.capstone.data.remote.dataSource.TripDataSource
import com.capstone.data.remote.dto.TimeTableDTO
import retrofit2.Retrofit
import javax.inject.Inject

class TimeTableDataSourceImpl @Inject constructor(private val retrofit: Retrofit): TimeTableDataSource {
    override suspend fun getTripTimeTable(trip_id: Int): TimeTableDTO {
        return retrofit.create(TimeTableDataSource::class.java).getTripTimeTable(trip_id)
    }

    override suspend fun getAllTimeTable(trip_id: Int): TimeTableDTO {
        return retrofit.create(TimeTableDataSource::class.java).getAllTimeTable(trip_id)
    }
}