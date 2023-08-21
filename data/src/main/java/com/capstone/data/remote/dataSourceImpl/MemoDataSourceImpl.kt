package com.capstone.data.remote.dataSourceImpl

import com.capstone.data.remote.dataSource.MemoDataSource
import com.capstone.data.remote.dto.MemoDTO
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class MemoDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): MemoDataSource {
    override suspend fun getMemo(trip_id: Int): MemoDTO {
        return retrofit.create(MemoDataSource::class.java).getMemo(trip_id)
    }

    override suspend fun postUrl(trip_id: Int, user_id: Int, content: String) {
        return retrofit.create(MemoDataSource::class.java).postUrl(trip_id, user_id, content)
    }

}