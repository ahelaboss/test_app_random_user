package com.yourgains.testapprandomuser.data.entity.network.response

import com.google.gson.annotations.SerializedName

data class DateResponse(
    @SerializedName("date") val date: String?,
    @SerializedName("age") val age: Int
)
