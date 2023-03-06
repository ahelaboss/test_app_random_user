package com.yourgains.testapprandomuser.data.entity.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserUI(
    val uuid: String,
    val login: String?,
    val titleName: String?,
    val firstName: String?,
    val lastName: String?,
    val gender: String?,
    val email: String?,
    val phone: String?,
    val cell: String?,
    val thumbnail: String?
) : Parcelable {

    fun getFullName() = "$titleName $firstName $lastName"
}
