package com.capstone.domain.model

import java.util.*

data class DomainTrip(
    val trip_id: Int,
    val trip_name: String,
    val trip_path: String,
    val start_date: String,
    val end_date: String
)
