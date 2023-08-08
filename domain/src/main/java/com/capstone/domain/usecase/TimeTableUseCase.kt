package com.capstone.domain.usecase

import com.capstone.domain.model.DomainTimeTable
import com.capstone.domain.repository.TimeTableRepository

class TimeTableUseCase (private val repository: TimeTableRepository) {
    suspend fun getTripTimeTable(trip_id : Int) : List<DomainTimeTable>?{
        return repository.getTripTimeTable(trip_id)
    }
    suspend fun getAllTimeTable(trip_id: Int) : List<DomainTimeTable>?{
        return repository.getAllTimeTable(trip_id)
    }
}