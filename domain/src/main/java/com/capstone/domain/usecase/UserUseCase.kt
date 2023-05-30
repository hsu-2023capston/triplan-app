package com.capstone.domain.usecase

import com.capstone.domain.model.DomainUser
import com.capstone.domain.repository.UserRepository

class UserUseCase(
    private val repository: UserRepository
) {
    suspend fun getUserLogin(access_token: String): DomainUser{
        return repository.getUserLogin(access_token)
    }

    suspend fun postUserLogin(user_name: String, access_token: String, default_id:Int) : DomainUser{
       return repository.postUserLogin(user_name,access_token,default_id)
    }
}