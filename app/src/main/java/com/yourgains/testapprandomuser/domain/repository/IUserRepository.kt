package com.yourgains.testapprandomuser.domain.repository

import com.yourgains.testapprandomuser.data.PendingResult
import com.yourgains.testapprandomuser.data.entity.db.UserDB
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI


interface IUserRepository {

    suspend fun getUsers(count: Int): PendingResult<List<UserUI>>

    suspend fun saveHistory(users: List<UserDB>)

    suspend fun getHistory(): PendingResult<List<UserUI>>

}