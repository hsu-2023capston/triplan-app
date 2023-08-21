package com.capstone.domain.repository

import com.capstone.domain.model.DomainMemo

interface MemoRepository {
    suspend fun getMemo(trip_id:Int): List<DomainMemo>

    suspend fun postUrl(trip_id: Int,user_id: Int, content: String)
}