package com.capstone.domain.repository

import com.capstone.domain.model.DomainGroup
import com.capstone.domain.model.DomainGroupName
import java.io.File
import com.capstone.domain.model.DomainUser

interface GroupRepository {
    suspend fun getGroup(user_id: Int): List<DomainGroup>

    suspend fun postGroup(group_name: String,group_pw: String,user_id:Int,group_path: File)

    suspend fun getGroupName(group_code: String): DomainGroupName

    suspend fun getGroupMember(group_id: Int):List<DomainUser>
}