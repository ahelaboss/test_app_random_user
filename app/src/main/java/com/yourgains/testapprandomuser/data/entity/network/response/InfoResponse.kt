package com.yourgains.testapprandomuser.data.entity.network.response

import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("seed") val seed: String?,
    @SerializedName("results") val results: Long?,
    @SerializedName("page") val page: Long?,
    @SerializedName("version") val version: String?
)
