package com.yourgains.testapprandomuser.data.entity.network.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("gender") val gender: String?,
    @SerializedName("name") val name: UserNameResponse?,
    @SerializedName("location") val location: LocationResponse?,
    @SerializedName("email") val email: String?,
    @SerializedName("login") val login: UserLoginResponse?,
    @SerializedName("dob") val dob: DateResponse?,
    @SerializedName("registered") val registered: DateResponse?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("cell") val cell: String?,
    @SerializedName("id") val id: IdResponse?,
    @SerializedName("picture") val picture: PictureResponse?,
    @SerializedName("nat") val nat: String?
)
