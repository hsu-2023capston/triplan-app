package com.capstone.data.remote.dto

import com.capstone.domain.model.DomainTrip
import java.util.Date

data class TripDto(
    val Data: List<Trip>
)
data class Trip(
    val trip_id: Int,
    val trip_name: String,
    val trip_path: String,
    val start_date: String,
    val end_date: String
){
    fun toDomainTrip() : DomainTrip{
        return DomainTrip(
            trip_id = trip_id,
            trip_name = trip_name,
            trip_path =trip_path,
            start_date = start_date,
            end_date = end_date
        )
    }
}