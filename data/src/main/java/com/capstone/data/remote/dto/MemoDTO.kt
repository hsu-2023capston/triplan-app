package com.capstone.data.remote.dto

import com.capstone.domain.model.DomainMemo


data class MemoDTO(
    val Message: String,
    val Data: List<Memo>
)

data class Memo(
    val classification: Int,
    val category: String,
    val user_id: Int,
    val content: String,
    val image_path: String,
    val content_datetime: String,
    val is_url: Int,
    val like_count: Int,
    val is_like: Int,
    val trip_id: Int,
    val user_name: String
){
    fun toDomainMemo(): DomainMemo{
        return DomainMemo(
            classification,
            category,
            user_id,
            content,
            image_path,
            content_datetime,
            is_url,
            like_count,
            is_like,
            trip_id,
            user_name
        )
    }
}
