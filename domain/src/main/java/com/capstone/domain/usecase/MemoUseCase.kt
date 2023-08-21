package com.capstone.domain.usecase

import com.capstone.domain.model.DomainMemo
import com.capstone.domain.repository.MemoRepository

class MemoUseCase (
    private val repository: MemoRepository
){
    suspend fun getMemo(trip_id: Int): List<DomainMemo>{
        return repository.getMemo(trip_id)
    }

    suspend fun postUrl(trip_id: Int, user_id:Int, content: String){
        return repository.postUrl(trip_id,user_id,content)
    }
}