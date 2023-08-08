package com.capstone.domain.repository

import com.capstone.domain.model.DomainTimeTable

interface TimeTableRepository {
    suspend fun getTripTimeTable(trip_id : Int) : List<DomainTimeTable>?
    suspend fun getAllTimeTable(trip_id: Int) : List<DomainTimeTable>?
}