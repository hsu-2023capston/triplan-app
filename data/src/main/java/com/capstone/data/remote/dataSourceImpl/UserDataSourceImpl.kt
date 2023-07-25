package com.capstone.data.remote.dataSourceImpl

import com.capstone.data.remote.dataSource.UserDataSource
import com.capstone.data.remote.dto.User
import retrofit2.Retrofit
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
):UserDataSource {
    override suspend fun getUserLogin(access_token: String): User {
        return retrofit.create(UserDataSource::class.java).getUserLogin(access_token)
    }

    override suspend fun postUserLogin(user_name:String, access_token:String, default_id:Int): User {
        return retrofit.create(UserDataSource::class.java).postUserLogin(user_name, access_token, default_id)
    }

}