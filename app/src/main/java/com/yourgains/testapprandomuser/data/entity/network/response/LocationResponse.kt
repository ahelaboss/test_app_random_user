package com.yourgains.testapprandomuser.data.entity.network.response

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("street") val street: LocationStreetResponse?,
    @SerializedName("city") val city: String?,
    @SerializedName("state") val state: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("postcode") val postcode: String?,
    @SerializedName("coordinates") val coordinates: LocationCoordinatesResponse?,
    @SerializedName("timezone") val timezone: LocationTimezoneResponse?
)
