package com.capstone.data.repositoryImpl

import com.capstone.data.remote.dataSource.TripDataSource
import com.capstone.domain.model.DomainTrip
import com.capstone.domain.repository.TripRepository

class TripRepositoryImpl(private val api: TripDataSource ) : TripRepository {
    override suspend fun getTrip(group_id: Int): List<DomainTrip> {
        return api.getTrip().Data.map { it.toDomainTrip() }
    }
}