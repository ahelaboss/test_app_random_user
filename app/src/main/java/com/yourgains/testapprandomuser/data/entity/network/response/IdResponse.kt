package com.yourgains.testapprandomuser.data.entity.network.response

import com.google.gson.annotations.SerializedName

data class IdResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("value") val value: String?
)
