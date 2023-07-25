package com.capstone.data.repositoryImpl

import com.capstone.data.remote.dataSource.GroupDataSource
import com.capstone.domain.model.DomainGroup
import com.capstone.domain.model.DomainGroupName
import com.capstone.domain.model.DomainUser
import com.capstone.domain.repository.GroupRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class GroupRepositoryImpl(
    private val api: GroupDataSource
): GroupRepository {
    override suspend fun getGroup(user_id: Int): List<DomainGroup> {
        return api.getGroup(user_id).Data.map { it.toDomainGroup() }
    }

    override suspend fun postGroup(
        group_name: String,
        group_pw: String,
        user_id: Int,
        group_path: File,
    ) {
        val requestFile = group_path.asRequestBody("image/png".toMediaTypeOrNull())
        val imgData = MultipartBody.Part.createFormData("group_path",group_path.name,requestFile)
//        val nameData = MultipartBody.Part.createFormData("group_name", group_name)
//        val pwData = MultipartBody.Part.createFormData("group_pw", group_pw)
//        val idData = MultipartBody.Part.createFormData("user_id", user_id.toString())


        return api.postGroup(group_name,group_pw,user_id,imgData)
    }

    override suspend fun getGroupName(group_code: String): DomainGroupName {
        return api.getGroupName(group_code).toDomainGroupName()
    }

    override suspend fun getGroupMember(group_id: Int): List<DomainUser> {
        return api.getGroupMember(group_id).Data.map { it.toDomainUser() }

    }

}