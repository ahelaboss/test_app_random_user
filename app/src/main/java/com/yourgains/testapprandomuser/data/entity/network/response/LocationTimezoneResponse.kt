package com.yourgains.testapprandomuser.data.entity.network.response

import com.google.gson.annotations.SerializedName

data class LocationTimezoneResponse(
    @SerializedName("offset") val offset: String?,
    @SerializedName("description") val description: String?
)
