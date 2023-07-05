package com.capstone.domain.usecase

import com.capstone.domain.model.DomainGroup
import com.capstone.domain.repository.GroupRepository
import java.io.File

class GroupUseCase(
    private val repository: GroupRepository
) {

    suspend fun getGroup(user_id: Int): List<DomainGroup>{
        return repository.getGroup(user_id)
    }

    suspend fun postGroup(group_name: String,group_pw: String,user_id:Int,group_path: File) {
        return repository.postGroup(group_name,group_pw,user_id,group_path)
    }
}