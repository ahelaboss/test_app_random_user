package com.yourgains.testapprandomuser.data.entity.network.response

import com.google.gson.annotations.SerializedName

data class LocationCoordinatesResponse(
    @SerializedName("latitude") val latitude: String?,
    @SerializedName("longitude") val longitude: String?
)
