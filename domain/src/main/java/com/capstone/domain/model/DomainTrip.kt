package com.capstone.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainTrip(
    val trip_id: Int,
    val trip_name: String,
    val trip_path: String,
    val start_date: String,
    val end_date: String
): Parcelable
