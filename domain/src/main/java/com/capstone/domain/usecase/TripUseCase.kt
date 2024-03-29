package com.capstone.domain.usecase

import android.util.Log
import com.capstone.domain.model.DomainTrip
import com.capstone.domain.model.DomainUser
import com.capstone.domain.repository.TripRepository

class TripUseCase(private val repository: TripRepository) {
    suspend fun getTrip(group_id: Int): List<DomainTrip> {
        return repository.getTrip(group_id)
    }
    suspend fun getTripMember(trip_id:Int):List<DomainUser>{
        return repository.getTripMember(trip_id)
    }

    suspend fun getTripHome(user_id: Int): List<DomainTrip> {
        return repository.getTripHome(user_id)
    }
}