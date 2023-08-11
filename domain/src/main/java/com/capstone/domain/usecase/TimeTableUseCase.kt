package com.capstone.domain.usecase

import com.capstone.domain.model.DomainTimeTable
import com.capstone.domain.repository.TimeTableRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimeTableUseCase (private val repository: TimeTableRepository) {
    suspend fun getTripTimeTable(trip_id : Int) : List<DomainTimeTable>?{
        return repository.getTripTimeTable(trip_id)
    }
    suspend fun getTimeTableDate(trip_id: Int) : List<String>? {
        return repository.getTripTimeTable(trip_id)?.map { it.start_date }?.distinct()
    }
    suspend fun getAllTimeTable(trip_id: Int) : List<DomainTimeTable>?{
        return repository.getAllTimeTable(trip_id)
    }
}