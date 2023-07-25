package com.capstone.data.remote.dataSourceImpl

import com.capstone.data.remote.dataSource.TripDataSource
import com.capstone.data.remote.dto.Trip
import com.capstone.data.remote.dto.TripDto
import retrofit2.Retrofit
import javax.inject.Inject

class TripDataSourceImpl @Inject constructor(private val retrofit: Retrofit): TripDataSource {
    override suspend fun getTrip(group_id:Int): TripDto {
        return retrofit.create(TripDataSource::class.java).getTrip(group_id)
    }
}