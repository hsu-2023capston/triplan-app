package com.capstone.data.repositoryImpl

import com.capstone.data.remote.dataSource.TripDataSource
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
}