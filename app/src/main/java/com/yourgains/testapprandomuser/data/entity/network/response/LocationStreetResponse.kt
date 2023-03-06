package com.yourgains.testapprandomuser.data.entity.network.response

import com.google.gson.annotations.SerializedName

data class LocationStreetResponse(
    @SerializedName("number") val number: Long,
    @SerializedName("name") val first: String?
)
