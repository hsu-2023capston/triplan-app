package com.capstone.data.remote.dto

import com.capstone.domain.model.DomainTimeTable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
            start_date = LocalDateTime.parse(start_date, DateTimeFormatter.ISO_DATE_TIME).toLocalDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")),
            start_time = LocalDateTime.parse(start_date, DateTimeFormatter.ISO_DATE_TIME).toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")),
            end_date =  LocalDateTime.parse(end_date, DateTimeFormatter.ISO_DATE_TIME).toLocalDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")),
            end_time = LocalDateTime.parse(end_date, DateTimeFormatter.ISO_DATE_TIME).toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")),
        )
    }
}