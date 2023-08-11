package com.capstone.domain.model

data class DomainTimeTable(
    val timetable_id :Int,
    val trip_id: Int,
    val title: String,
    val memo: String?,
    val start_date: String,
    val start_time: String,
    val end_date: String,
    val end_time: String
)
