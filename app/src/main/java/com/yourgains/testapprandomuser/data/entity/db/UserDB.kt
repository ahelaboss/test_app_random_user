package com.yourgains.testapprandomuser.data.entity.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDB(
    @PrimaryKey
    @ColumnInfo(name = "id") val uuid: String,
    @ColumnInfo(name = "login") val login: String?,
    @ColumnInfo(name = "titleName") val titleName: String?,
    @ColumnInfo(name = "firstName") val firstName: String?,
    @ColumnInfo(name = "lastName") val lastName: String?,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "cell") val cell: String?,
    @ColumnInfo(name = "thumbnail") val thumbnail: String?
)
