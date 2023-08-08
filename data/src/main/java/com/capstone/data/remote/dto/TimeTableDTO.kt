package com.capstone.data.remote.dto

import com.capstone.domain.model.DomainTimeTable

data class TimeTableDTO(
    val Message: String,
    val Data: List<TimeTable>?
)

data class TimeTable(
    val timetable_id: Int,
    val trip_id: Int,
    val title: String,
    val memo: String?,
    val start_date: String,
    val end_date: String
) {
    fun toDomainTimeTable(): DomainTimeTable {
        return DomainTimeTable(
            timetable_id = timetable_id,
            trip_id = trip_id,
            title = title,
            memo = memo,
            start_date = start_date,
            end_date = end_date
        )
    }
}