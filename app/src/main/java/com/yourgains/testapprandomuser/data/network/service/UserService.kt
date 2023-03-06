package com.yourgains.testapprandomuser.data.network.service

import com.yourgains.testapprandomuser.data.entity.network.response.UserListResponse
import com.yourgains.testapprandomuser.data.network.service.BaseService.Companion.API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService : BaseService {

    companion object {
        private const val GET_USERS = "$API/"

        private const val RESULTS = "results"
    }

    @GET(GET_USERS)
    fun getUsers(@Query(RESULTS) count: Int): Call<UserListResponse>
}