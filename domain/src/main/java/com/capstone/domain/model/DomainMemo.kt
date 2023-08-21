package com.capstone.domain.model

data class DomainMemo(
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
)
