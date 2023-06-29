package com.capstone.data.repositoryImpl

import com.capstone.data.remote.dataSource.UserDataSource
import com.capstone.domain.model.DomainUser
import com.capstone.domain.repository.UserRepository
import com.google.gson.JsonObject

class UserRepositoryImpl(
    private val api: UserDataSource
): UserRepository {
    override suspend fun getUserLogin(access_token: String): DomainUser {
        return api.getUserLogin(access_token).toDomainUser()
    }

    override suspend fun postUserLogin(
        user_name: String,
        access_token: String,
        default_id: Int,
    ): DomainUser {
//        val jsonData : JsonObject = JsonObject().apply {
//            addProperty("user_name",user_name)
//            addProperty("access_token",access_token)
//            addProperty("default_id",default_id)
//        }
//        val data = api.postUserLogin(user_name, access_token, default_id)
//        return data.toDomainUser()
        return api.postUserLogin(user_name, access_token, default_id).toDomainUser()
    }
}