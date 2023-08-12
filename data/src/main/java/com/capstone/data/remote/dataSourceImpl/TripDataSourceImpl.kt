package com.capstone.data.remote.dataSourceImpl

import com.capstone.data.remote.dataSource.TripDataSource
import com.capstone.data.remote.dto.ListTripDTO
import com.capstone.data.remote.dto.TripDTO
import com.capstone.data.remote.dto.UserDTO
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class TripDataSourceImpl @Inject constructor(private val retrofit: Retrofit): TripDataSource {
    override suspend fun getTrip(group_id:Int): TripDTO {
        return retrofit.create(TripDataSource::class.java).getTrip(group_id)
    }

    override suspend fun getTripMember(trip_id: Int): UserDTO {
        return retrofit.create(TripDataSource::class.java).getTripMember(trip_id)
    }

    override suspend fun getTripHome(user_id: Int): ListTripDTO {
        return retrofit.create(TripDataSource::class.java).getTripHome(user_id)
    }
}