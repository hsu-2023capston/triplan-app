package com.capstone.domain.repository

import com.capstone.domain.model.DomainGroup
import com.capstone.domain.model.DomainTrip

interface TripRepository {
    suspend fun getTrip(group_id: Int): List<DomainTrip>
}