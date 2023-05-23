package com.capstone.domain.repository

import com.capstone.domain.model.DomainUser

interface UserRepository {
    suspend fun getUserLogin(access_token: String) : DomainUser

    suspend fun postUserLogin(user_name: String, access_token: String, default_id:Int) : DomainUser
}