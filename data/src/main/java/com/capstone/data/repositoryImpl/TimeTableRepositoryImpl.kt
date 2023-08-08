package com.capstone.data.repositoryImpl

import com.capstone.data.remote.dataSource.TimeTableDataSource
import com.capstone.domain.model.DomainTimeTable
import com.capstone.domain.repository.TimeTableRepository
import javax.inject.Inject

class TimeTableRepositoryImpl @Inject constructor (private val api: TimeTableDataSource
): TimeTableRepository {
    override suspend fun getTripTimeTable(trip_id: Int): List<DomainTimeTable>? {
        return api.getTripTimeTable(trip_id).Data?.map { it.toDomainTimeTable() }
    }

    override suspend fun getAllTimeTable(trip_id: Int): List<DomainTimeTable>? {
        return api.getAllTimeTable(trip_id).Data?.map { it.toDomainTimeTable() }
    }
}