package com.capstone.domain.usecase

import android.util.Log
import com.capstone.domain.model.DomainTrip
import com.capstone.domain.repository.TripRepository

class TripUseCase(private val repository: TripRepository) {
    suspend fun getTrip(group_id: Int): List<DomainTrip> {
        return repository.getTrip(group_id)
    }
}