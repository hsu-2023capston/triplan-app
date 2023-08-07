package com.capstone.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainGroup (
    val group_id: Int,
    val group_name: String,
    val group_code: String,
    val group_pw: String,
    val group_path: String
    ): Parcelable