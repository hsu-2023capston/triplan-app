package com.capstone.data.repositoryImpl

import com.capstone.data.remote.dataSource.TripDataSource
import com.capstone.data.remote.dto.Trip
import com.capstone.domain.model.DomainTrip
import com.capstone.domain.model.DomainUser
import com.capstone.domain.repository.TripRepository

class TripRepositoryImpl(private val api: TripDataSource ) : TripRepository {
    override suspend fun getTrip(group_id: Int): List<DomainTrip> {
        return api.getTrip(group_id).Data.map { it.toDomainTrip() }
    }

    override suspend fun getTripMember(trip_id: Int): List<DomainUser> {
        return api.getTripMember(trip_id).Data.map { it.toDomainUser()}
    }

    override suspend fun getTripHome(user_id: Int): List<DomainTrip> {
        return api.getTripHome(user_id).Data.flatten().map { it.toDomainTrip() }
    }

}