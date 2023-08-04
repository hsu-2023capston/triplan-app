package com.capstone.domain.repository

import com.capstone.domain.model.DomainGroup
import com.capstone.domain.model.DomainTrip
import com.capstone.domain.model.DomainUser

interface TripRepository {
    suspend fun getTrip(group_id: Int): List<DomainTrip>
    suspend fun getTripMember(trip_id:Int):List<DomainUser>
}